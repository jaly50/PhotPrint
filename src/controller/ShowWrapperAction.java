package controller;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import model.Model;
import model.PhotoDAO;
import model.Photo_FavorDAO;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import formbeans.ShowWrapper;
import databeans.Photo_Favor;
import databeans.WrapperTable;

/**
 * 
 */

public class ShowWrapperAction extends Action {
	private FormBeanFactory<ShowWrapper> formBeanFactory = FormBeanFactory
			.getInstance(ShowWrapper.class);

	private UserDAO userDAO;
	private PhotoDAO photoDAO;
	private static Photo_FavorDAO photo_FavorDAO;

	static String apiKey = "b64b6e7af8762bd7b36eb3efe360fbf0";
	static String secret = "5afa0fdb4d420d3d";
	static String per_page = "5";
	static int tagNum = 0;
	static String[] photo;
	static String[] url;
	static String[] title;
	// static String[] location;
	static ArrayList<String[]> tags;
	static int[] count_like;
	static int[] count_dislike;

	public ShowWrapperAction(Model model) {
		userDAO = model.getUserDAO();
		photoDAO = model.getPhotoDAO();
		photo_FavorDAO = model.getPhoto_FavorDAO();
	}

	public String getName() {
		return "showWrapper.do";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		String message;
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();
		String description = (String) session.getAttribute("description");
		//String description = "#goldman #wallstreet #wolf";
		String location = (String) session.getAttribute("location");
		//String location = "Europe";
		int countTags = 0;
		int[] tagPosition = new int[10];
		Arrays.fill(tagPosition, 0);
		for (int i = 0, j = 0; i < description.length(); i++) {
			if (description.charAt(i) == '#') {
				countTags++;
				tagPosition[j] = i;
				j++;
			}
		}
		if (countTags > 8) {
			errors.add("Too many tags dear.");
			return "showWrapper.jsp";
		}
		tagNum = countTags;
		int start = 0;
		int end = 0;
		int tag = 0; // 0 means last point is end point, 1 means last point is
						// start point;
		String[] subString = new String[countTags];
		for (int i = 0, j = 0; i < tagPosition.length && j < countTags; i++) {

			if (i == 0) {
				start = tagPosition[i];
				tag = 1;
				continue;

			}
			if (tagPosition[i] != 0 && tag == 1) {
				end = tagPosition[i];
				subString[j] = description.substring(start + 1, end - 1).trim();
				start = end;
				j++;
				continue;
			}
			subString[j] = description.substring(start + 1,
					description.length()).trim();

		}

		
		request.setAttribute("subString", subString);
		request.setAttribute("location", location);
		session.setAttribute("subString", subString);
		session.setAttribute("location", location);

		try {
			ShowWrapper form;
			form = formBeanFactory.create(request);
			
			request.setAttribute("showWrapper", form);
			List<WrapperTable> WrapperTable = new ArrayList<WrapperTable>();
			List<Photo_Favor> PhotoFavTable = new ArrayList<Photo_Favor>();
			String tag1 = "Michael";
			String tag2 = "Jordan";
			if (countTags == 0)
				searchPhoto(location, location);
			else {
				for (int m = 0; m < subString.length; m++) {
					searchPhoto(location, subString[m]);
					// set WrapperTable
					for (int i = 0; i < photo.length; i++) {
						WrapperTable wTableRow = new WrapperTable();
						wTableRow.setPhoto(photo[i]);
						wTableRow.setUrl(url[i]);
						wTableRow.setTitle(title[i]);
						wTableRow.setTags(tags.get(i));
						wTableRow.setCount_like(count_like[i]);
						wTableRow.setCount_dislike(count_dislike[i]);
						WrapperTable.add(wTableRow);

						Photo_Favor pTableRow = new Photo_Favor();
						pTableRow.setPhoto(photo[i].toString().trim());
						pTableRow.setTitle(title[i].trim());
						pTableRow.setUrl(url[i].trim());
						pTableRow.setLocation(location.trim());
						pTableRow.setTag(subString[m].trim());
						pTableRow.setCount_Like(count_like[i]);
						pTableRow.setCount_Dislike(count_dislike[i]);
						PhotoFavTable.add(pTableRow);
						photo_FavorDAO.updatePhotoFavor(pTableRow);
					}
				}
			}
			// getPhotoInfo(tag1,tag2);

			// get count_like
			/*
			 * System.out.println(photo.length); for (int i = 0; i <
			 * photo.length; i++) { if (tags.get(i) == null ||
			 * tags.get(i).length == 0) { System.out.println("fault");
			 * System.exit(0); } System.out.println(photo[i]);
			 * System.out.println(title[i]); }
			 */

			request.setAttribute("WrapperTable", WrapperTable);
			if (!form.isPresent()) {
				return "showWrapper.jsp";
			}

			//System.out.println("ShowWrapper");

		} catch (IOException | XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "showWrapper.jsp";
		} catch (FormBeanException e1) {
			// TODO Auto-generated catch block
			errors.add(e1.getMessage());
			errors.add("FormBean Exception.");
			return "showWrapper.jsp";
		}

		// request.setAttribute("messages", message);
		return "showWrapper.jsp";

	}

	public static void showPhoto(String id) {

	}

	/*******************************************************************************/

	// 1. search photo by key words.
	public static void searchPhoto(String tag1, String tag2)
			throws MalformedURLException, IOException, XMLStreamException {

		String method = "flickr.photos.search";
		int count = Integer.parseInt(per_page);

		/*
		 * URLConnection uc = new URL(
		 * "https://api.flickr.com/services/rest/?method=" + method +
		 * "&api_key=" + apiKey + "&per_page=" + per_page + "&text=" +
		 * text1).openConnection();
		 */

		/*
		 * URLConnection uc = new URL(
		 * "https://api.flickr.com/services/rest/?method=" + method +
		 * "&api_key=" + apiKey + "&per_page=" + per_page + "&text=" + text1
		 * +",+" + text2).openConnection();
		 */

		URLConnection uc = new URL(
				"https://api.flickr.com/services/rest/?method=" + method
						+ "&api_key=" + apiKey + "&per_page=" + per_page
						+ "&tags=" + tag1 + ",+" + tag2 + "&tag_mode=all")
				.openConnection();
		/*
		 * System.out.println("https://api.flickr.com/services/rest/?method=" +
		 * method + "&api_key=" + apiKey + "&per_page=" + per_page + "&tags=" +
		 * tag1 +",+" + tag2 + "&tag_mode=all");
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(
				uc.getInputStream()));
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				"/Users/Charlotte/Documents/picturesfortest/searchPhoto.xml")));

		String nextline;
		String[] servers = new String[count];
		String[] ids = new String[count];
		String[] secrets = new String[count];
		photo = new String[count];
		url = new String[count];
		title = new String[count];
		// location = new String[count];
		tags = new ArrayList<String[]>(count);
		count_like = new int[count];
		count_dislike = new int[count];

		while ((nextline = br.readLine()) != null) {

			bw.write(nextline);// fastest the way to read and write
		}

		br.close();
		bw.close();

		String filename = "/Users/Charlotte/Documents/picturesfortest/searchPhoto.xml";
		XMLInputFactory factory = XMLInputFactory.newInstance();

		XMLEventReader r = factory.createXMLEventReader(filename,
				new FileInputStream(filename));
		int i = -1;
		while (r.hasNext()) {
			XMLEvent event = r.nextEvent();
			if (event.isStartElement()) {
				StartElement element = (StartElement) event;
				String elementName = element.getName().toString();

				if (elementName.equals("photo")) {// xml element starts with
													// photo
					i++;
					Iterator iterator = element.getAttributes();

					while (iterator.hasNext()) {

						Attribute attribute = (Attribute) iterator.next();
						QName name = attribute.getName();
						String value = attribute.getValue();
						if ((name.toString()).equals("server")) {
							servers[i] = value;
						}
						if ((name.toString()).equals("id")) {
							ids[i] = value;
						}
						if ((name.toString()).equals("secret")) {
							secrets[i] = value;
						}

					}
				}
			}
		}
		

		for (int j = 0; j < i + 1; j++) {
			String photorurl = "http://static.flickr.com/" + servers[j] + "/"
					+ ids[j] + "_" + secrets[j] + ".jpg";
			
			// get photo[]
			photo[j] = photorurl;
			
			// get count_like
			try {
				count_like[j] = photo_FavorDAO.getCount_Like(photo[j]);
			} catch (RollbackException e) {
				
				e.printStackTrace();
			}
			
			// get count_dislike
			try {
				count_dislike[j] = photo_FavorDAO.getCount_Dislike(photo[j]);
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		getPhotoInfo(ids, secrets);
	}

	/********************************************************************************/
	// 2. get photo information by photo id and secret.
	@SuppressWarnings("null")
	public static void getPhotoInfo(String[] ids, String[] secrets)
			throws XMLStreamException, MalformedURLException, IOException {
		String method = "flickr.photos.getInfo";
		int count = Integer.parseInt(per_page);
		String[] servers = new String[count];
		ids = new String[count];
		secrets = new String[count];

		String filename = "/Users/Charlotte/Documents/picturesfortest/searchPhoto.xml";
		XMLInputFactory factory = XMLInputFactory.newInstance();

		XMLEventReader r = factory.createXMLEventReader(filename,
				new FileInputStream(filename));
		int i = -1;
		while (r.hasNext()) {
			XMLEvent event = r.nextEvent();
			if (event.isStartElement()) {
				StartElement element = (StartElement) event;
				String elementName = element.getName().toString();

				if (elementName.equals("photo")) {// xml element starts with
													// photo
					i++;
					Iterator iterator = element.getAttributes();

					while (iterator.hasNext()) {

						Attribute attribute = (Attribute) iterator.next();
						QName name = attribute.getName();
						String value = attribute.getValue();
						if ((name.toString()).equals("server")) {
							servers[i] = value;
						}
						if ((name.toString()).equals("id")) {
							ids[i] = value;
						}
						if ((name.toString()).equals("secret")) {
							secrets[i] = value;
						}
					}
				}
			}
		}
		// System.out.println(i);

		URLConnection[] ucs = new URLConnection[count];
		for (int n = 0; n < count; n++)
			ucs[n] = null;
		
		// write photo ids[m] to photo_id.xml
		for (int m = 0; m < count; m++) {
			ucs[m] = new URL("https://api.flickr.com/services/rest/?method="
					+ method + "&api_key=" + apiKey + "&photo_id=" + ids[m]
					+ "&secret=" + secrets[m]).openConnection();

			BufferedReader br = new BufferedReader(new InputStreamReader(
					ucs[m].getInputStream()));
			String newFileName = String.format(
					"/Users/Charlotte/Documents/picturesfortest/searchPhoto.xml", ids[m]);
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
					newFileName)));

			String nextline;
			String[] title = new String[count];
			String[] descriptions = new String[count];
			String[] dates = new String[count];
			while ((nextline = br.readLine()) != null) {
				bw.write(nextline);// fastest the way to read and write
			}

			br.close();
			bw.close();
		}

		// read photo files;
		for (int m = 0; m < count; m++) {
			
			// System.out.println(ids[m]);
			String infoFileName = String.format(
					"/Users/Charlotte/Documents/picturesfortest/searchPhoto.xml", ids[m]);
			String[] temp = new String[Integer.parseInt(per_page)];
			int tag_num = 0;

			// get location from photo_xxx
			/*
			 * XMLInputFactory Infofactory = XMLInputFactory.newInstance();
			 * XMLEventReader infoR = Infofactory.createXMLEventReader(filename,
			 * new FileInputStream(filename)); int infoI = -1; while
			 * (infoR.hasNext()) { XMLEvent event = infoR.nextEvent(); if
			 * (event.isStartElement()) { StartElement element = (StartElement)
			 * event; String elementName = element.getName().toString();
			 * 
			 * if (elementName.equals("owner")) {// xml element starts with //
			 * photo infoI++; Iterator iterator = element.getAttributes();
			 * 
			 * while (iterator.hasNext()) {
			 * 
			 * Attribute attribute = (Attribute) iterator.next(); QName name =
			 * attribute.getName(); String value = attribute.getValue(); if
			 * ((name.toString()).equals("location")) { location[infoI] = value;
			 * }
			 * 
			 * } } } }
			 */

			DocumentBuilderFactory domFactory = DocumentBuilderFactory
					.newInstance();
			try {
				DocumentBuilder builder = domFactory.newDocumentBuilder();
				Document doc = builder.parse(infoFileName);
				doc.getDocumentElement().normalize();
				
				/*
				 * NodeList urlList = doc.getElementsByTagName("url"); Node
				 * urlNode = urlList.item(0); photo[m]=
				 * urlNode.getTextContent();
				 */

				// get url
				NodeList urlList = doc.getElementsByTagName("url");
				Node urlNode = urlList.item(0);
				url[m] = urlNode.getTextContent();

				// get title
				NodeList titleList = doc.getElementsByTagName("title");
				Node titleNode = titleList.item(0);
				if (titleNode.getTextContent().length() > 20)
					title[m] = titleNode.getTextContent().substring(0, 20);
				else
					title[m] = titleNode.getTextContent();
				
				// get tags
				NodeList tagsList = doc.getElementsByTagName("tag");
				String[] tagsTemp = new String[3];
				for (int tempRow = 0; tempRow < 3; tempRow++) {
					Node tagNode = tagsList.item(tempRow);
					tagsTemp[tempRow] = tagNode.getTextContent();

				}
				tags.add(tagsTemp);
				
				
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
