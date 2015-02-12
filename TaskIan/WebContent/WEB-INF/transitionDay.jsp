<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<jsp:include page="message.jsp" />

<script type="text/javascript" src="js/epoch_classes.js"></script>
<link rel="stylesheet" type="text/css" href="css/epoch_styles.css" />


<script type="text/javascript" > 



function myfilter(e) {
	 var obj=e.srcElement || e.target;
	 var dot=obj.value.indexOf(".");//alert(e.which);
	 var  key=e.keyCode|| e.which;
	 if(key==8 || key==9 || key==46 || (key>=37  && key<=40))//to satisfy certain keys.
	  return true;
	 if (key<=57 && key>=48) { //number
	  if(dot==-1)//dot
	     return true;
	    else if(obj.value.length<=dot+3)//two decimal
	  return true;
	 } else if((key==46) && dot==-1){//dot number
	  return true;
	 }        
	    return false;
	}

function verification() {
	var text = document.getElementById("shares").value;
	  if (text == '') {
	     alert("Please Input Buying Amount");
	     return false;
	  }
 	
  
  return true;
}


function check(lbl){
    var txtval=lbl.value;        
    var key = event.keyCode;  
    if((key < 48||key > 57)&&key != 46){    
     event.keyCode = 0; 
     alert("Input number please"); 
    }else{  
     if(key == 46){  
      if(txtval.indexOf(".") != -1||txtval.length == 0)  
       event.keyCode = 0; 
       alert("Input number please"); 
     }  
   }  
}

var bas_cal, dp_cal, ms_cal; // declare the calendars as global variables
window.onload = function () {
  /*initialize any calendars on the page - in this case 3.*/
  dp_cal  = new Epoch('dp_cal','popup',document.getElementById('newDate'));
}; 

</script>


<div class="container">
 <h2>Transition Day</h2>
 <br>
 
<form method="post" action="transitionDay.do">

<div class="col-lg-6" style="margin-left:330px" >
 <div class="input-group"  > 
  <span class="input-group-btn">
  <span style="font-size:30px;"  class="glyphicon glyphicon-calendar"  aria-hidden="true" onclick="dp_cal.toggle();" > </span>
  </span>
  <h4>
 <input type="text" id="newDate" name="newDate" placeholder="New Transition Date" class="form-control" style="width: 280"  />
 <input class="btn btn-default" type="submit" name="button" value="Submit"/>
  </h4>
     
</div>
</div>

  <h4>Last Transition Day:${latestDate}</h4>
 <br>
 <br>

 <table class="table table-bordered">
    <thead>
      <tr>
        <th><p align="left">Fund ID</th>
        <th><p align="left">Fund Name</th>
        <th><p align="left">Fund Ticker</th>
        <th><p align="right">Last Transition Day Price</th>
        <th><p align="center">Set New Price</th>

      </tr>
    </thead>
    <tbody>
      
     <c:forEach var="f" items="${TransiFundTable}">    
      <tr>
        <td>${f.fund_id }</td>
        <td>${f.name}</td> 
        <td>${f.symbol}</td>
         <td align = "right">${f.latestPrice}</td>
        <td align="center"><input type="text" id="price" placeholder="$1-10,000"  name="price" value=""/></td>
      </tr>
     </c:forEach>
    </tbody>
    
  </table>

 <br />
 
 
 <br />
 <br />
 <br />
</form>
</div>

 

<jsp:include page="template-bottom.jsp" />
