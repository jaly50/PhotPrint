<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="template-top.jsp" />
<jsp:include page="error-list.jsp" />
<jsp:include page="message.jsp" />


<script type="text/javascript"> 

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
	     alert("Please Input shares to sell");
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


</script>


<div class="container">
<br />
 <br />
 <br />
 <h2>My Fund Position</h2>
<br />
<h5>Funds position:</h5>
 
<form method="post" action="sellFund.do">

 <table class="table table-bordered">
    <thead>
      <tr>
        <th><p align = left>Fund Name</th>
        <th><p align = left>Fund Ticker</th>
        <th><p align = right>Available Shares</th>
        <th><p align = center>Select to Sell</th>
      </tr>
    </thead>
    <tbody>
      
     <c:forEach var="f" items="${sellFundTable}">    
      <tr>
        <td><p align = left>${f.fundName }</td>
        <td><p align = left>${f.symbol }</td>
        <td><p align = "right">${f.availableShares}</td>
        <td><p align="center"><input type="radio" id="symbol" name="select" value="${f.fund_id}"></td>
     
      </tr>
  </c:forEach>
    </tbody>
    
  </table>

 <br />
 <h4 align="center">  Shares to Sell: 
      <input type="text" id="shares" name="shares" placeholder="1-1,000,000"
  	  style="ime-mode:disabled" onkeypress="return myfilter(event)"
      /> 
      <input class="btn btn-default" type="submit" value="Sell!" onClick="return verification()">
 </h4>
 
 <br />
 <br />
 <br />
</form>
</div>

<jsp:include page="template-bottom.jsp" />
