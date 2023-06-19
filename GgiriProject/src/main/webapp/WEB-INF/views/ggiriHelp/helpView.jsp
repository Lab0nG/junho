<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>helpView</title>
<script src="https://code.jquery.com/jquery-3.6.4.js" integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E=" crossorigin="anonymous"></script>
<script type="text/javascript">
	var contextPath = "${pageContext.request.contextPath}";
	
	$(function(){
		adminRepList();
	});
	
	function adminRepResult() {
		
		let helpNo = $("#adminHelpNo");
		let helpMember = $("#helpMember").val();
		let adminRep = $("#adminRep").val();
		
		console.log(helpNo);
		console.log(helpMember);
		console.log(adminRep);
		
		let form = { helpNo:helpNo, helpMember:helpMember, adminRep:adminRep}	
		
		$.ajax({
		      url: contextPath + "/ggiriAdmin/adminHelpReply", 
		      type: "post",
		      data: JSON.stringify(form),
		      contentType: "application/json; charset=UTF-8",
		      success: function(data) {
		         if(data == 1){
		        	 adminRepList();
		            $("#adminRep").val("");
		         } else {
		            alert("답변달기 실패");
		         }
		      },
		      error: function() {
		         alert("관리자 답변 등록 오류")
		      }
		   });
		
	}
	
	function adminRepList() {
		   
	   let helpNo = $("#helpNo").val();
	   console.log(helpNo);
	   
	   $.ajax({
	      url: contextPath + "/ggiriAdmin/adminRepData?helpNo=" + helpNo,
	      type: "get",
	      success: function(rep) {
	    	 console.log(rep[0]);
	         let html = "";
	         
             if(rep[0] != null){
		         let date = new Date(rep[0].adminRepDate);
	             let adminRepDate = date.getFullYear()+"년 "+(date.getMonth()+1)+"월 ";
	             adminRepDate += date.getDate()+"일 "+date.getHours()+"시 ";
	             adminRepDate += date.getMinutes()+"분 "+date.getSeconds()+"초";
	             html += "      <br><hr><div id='adminReply'>";
	             html += "         <table class='table'>";
	             html += "             <tr>";
	             html += "                <th width='150px' height='40px'> Ggiri 관리자 </th>"+"<td width='150px'>"+ rep[0].adminRepDate +"</td>";
	             html += "            </tr>";
	             html += "            <tr>";
	             html += "               <pre><td width='850px'>"+ rep[0].adminRep +"</td></pre>";
	             html += "            </tr>";
	             html += "         </table>";
	             html += "      </div>";
	             
	        	 $("#adminRepDiv").html(html);
             } else {
            	 html += "<br><hr><br><div> 해당 문의사항은 빠른시간내에 답변 드리겠습니다. </div>";
	        	 $("#adminRepDiv").html(html);
             }
	      },
	      error : function(){
	    	  alret("에러 운영자 답변 불러오기 실패");
	   	  }
	  });
	}
	
</script>
<style type="text/css">

* {
	margin: 0 auto;
}
.helpViewSize {
	width: 800px;
	margin-left: 150px;
}
input[type=button] {
    background-color: #E2EAE9 ;
    color: #333333;
    border: none;
    width: 100px;
    height: 30px;
    font-size: 14px;
	border-radius: 40px 80px / 80px 40px;
    cursor: pointer;
}
input[type=button]:hover {
    background-color: #EBF7FF;
    transition: 0.5s;
}
hr {
    background: gray;
    height: 1px;
    border: 0;
}
</style>
</head>
<body>
	<c:import url="../default/header.jsp"></c:import>
	<div class="helpViewSize">
		<input type="hidden" id="helpNo" name="helpNo" value="${data.helpNo }">
		<b> 문의 번호 ) &nbsp; </b>${data.helpNo }<br>
		<div id="head">
			<b> 회원 아이디 ) &nbsp; </b> ${data.id }<br><b> 제목 ) &nbsp; </b> ${data.title }
			<p style="float: right">${data.helpDate }</p><b style="float: right"> 문의 날짜 ) &nbsp; </b>
		</div>
		<hr>
		<div>
			<b>문의 내용 )</b><p style="height: 80px; border: 1px solid gray; padding: 5px">${data.content }</p>
		</div>
		<br>
		<c:if test="${data.id==loginUser || data.id == kakaoMember.id || data.id == googleMember.id || data.id == naverMember.id }">
			<td colspan="4" align="right">
				<input type="button" value="수정" onclick="location.href='../ggiriHelp/helpModifyForm?helpNo=${data.helpNo }'"> &nbsp;
				<input type="button" value="삭제" onclick="location.href='../ggiriHelp/helpDelete?helpNo=${data.helpNo }'"> &nbsp;
			</td>
		</c:if>
		<c:if test="${loginUser == 'GGIRIADMIN' }">
			<div class="adminHelpReplySize">
				<div class="adminHelpRep">
					<input type="hidden" id="helpMember" name="helpMember" value="${data.id }">
					<input type="hidden" id="adminHelpNo" name="adminHelpNo" value="${data.helpNo }">
					<h4> 답변 내용 </h4>
					<textarea rows="10" cols="50" id="adminRep" name="adminRep" style="resize: none;"></textarea><br>
					<button type="button" id="adminRepResult" name="adminRepResult" onclick="adminRepResult()"> 완료 </button>
				</div>
			</div>
		</c:if>
		<div id="adminRepDiv">
			
		</div>
	</div>
	<c:import url="../default/footer.jsp"></c:import>
</body>
</html>