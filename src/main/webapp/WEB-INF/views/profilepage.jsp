<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*,com.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/profilesStyle.css">
<script type="text/javascript" src='resources/JS/commentpostvalidation.js'></script> 
<!-- dark model below-->
<link rel="stylesheet" href="resources/CSS/darkmode.css">
<script type="text/javascript" src="resources/JS/darkmode.js"></script>
</head>
<body>
<div class="profilecontainer">

	<div class="menuprofile">
	<%@ include file="menus.jsp" %>
	</div><!-- menuprofile -->
	
	
	<div class="userprofile">
		
		<%
		List<ProfileInformationModel> profileList =(List<ProfileInformationModel>) request.getAttribute("prof");
		for(ProfileInformationModel prof :profileList){
			%>
			
		<div class="profilemain">
			<div class="profilelogo">
			<img alt="" src="resources/Profile_Images/<%=prof.getProfilephoto() %>">
			</div>
			<div class="profilename">
			
				<div class="profileUsername"><%=prof.getUsername() %></div>
				<div class="pff">
				<div id="padj">
				<a href="profilepage"><%=prof.getPostCount()%><br> Posts</a>
				</div>
				<div id="padj">
				<a href="followingpage"><%=prof.getFollowingCount()%> <br>Following</a>
				</div>
				<div id="padj">
				<a href="followerpage"><%=prof.getFollowerCount()%> <br>Followers</a>
				</div>
				
				</div>
				
				<div id="prodiv1">
				<div id="prodiv2">
					<div class="profileNam"><%=prof.getCustomername()%></div>
					<div class="bio"><%=prof.getBio()%></div>
				</div>
				<div id="prodiv3">
					<form name="frm" action="editprofilepage" >
					<button id="editprofile">Edit</button>
					</form>
				</div>
				</div>
			<%
			}
			%>
			</div>
		</div> <!-- profilemain -->
		
			<div class="allposts">
		
		
					<ul class="nav nav-tabs" id="myTab" role="tablist">
					  <li class="nav-item" role="presentation">
					    <button class="nav-link active " id="posts-tab" data-bs-toggle="tab" data-bs-target="#posts" type="button" role="tab" aria-controls="posts" aria-selected="true">Posts</button>
					  </li>
					</ul>
					<div class="tab-content" id="myTabContent">
					  <div class="tab-pane fade show active" id="posts" role="tabpanel" aria-labelledby="posts-tab">
					 
							 <%
					            List<PostLayoutModel> postsList = (List<PostLayoutModel>) request.getAttribute("posts");
					            if (postsList != null) {
					                for (PostLayoutModel posts : postsList) {
			       				%>
			                	<div class="pro">
			                	<div class="userlogo">
			                    <img alt="" src="resources/Profile_Images/<%=posts.getProfileimage() %>" width="100px" height="50px">
			                    </div>
			                    
			                    <div class="userpost">
			                    	<div id="userP">
			                    	
			                   		 <h4><%=posts.getUsername() %></h4> 
			                   		 <a id="deletepost" href="deletepost?postid=<%=posts.getPostid()%>" ><i class="fa-solid fa-trash-can"></i></a>
			                  		</div>
			                    <div id="postdisplay"><%=posts.getPost() %></div>
			                    <img alt="" src="resources/Post_Images/<%=posts.getImgname()%>">
				                   
				                    <div id="likecommentdiv">
				                    <div class="likesp">
				                    
				         				 <div id="likeGrid<%= posts.getPostid() %>">
						                 <%
						                
						                 int v = posts.getLike();
										 if(v>0){
											 %>			 
							                    <a id="liked" onclick="unlikefun(<%=posts.getPostid() %>)"> <i class="fa-solid fa-heart"></i> <%=posts.getLikeCount() %></a>
							                  <%
										 }else{
											 %>
											<a id="like"  onclick="likefun(<%=posts.getPostid() %>)"> <i class="fa-solid fa-heart"></i> <%=posts.getLikeCount() %></a>
					 	                    <%
										 }
						                 %> 
				                     </div>
				                    
				                   </div>  
				                    
				                    <div class="commentsp" id="commentGrid<%=posts.getPostid() %>">
				                    <a id="commentshow" href="viewprofilecomment?postid=<%=posts.getPostid()%>"> <i class="fa-solid fa-comment"></i> <%=posts.getCommentCount() %></a>
				                    <form name="frm" id="frm" method="POST" onsubmit="return commentfun1(<%=posts.getPostid() %>,comment.value)"> 
				                    <input type="text" name="comment" id="comment" placeholder="comment here..." required> 
				                    <button type="submit" id="commentbtn"  name="commentbtn" >post</button>
				                    </form>
				                    </div>
					                    
				                   
				                   
			                    </div>
			                    </div>
			                    </div>
			                    <%
			                    }
            				}else{
            					%>
            					<h5>Post Not Available</h5>
            					<%
            				}
					  	%>
					  	
					
					  </div>
		</div>
			</div> <!-- allposts -->

		
		
	</div><!-- userprofile -->
</div>
</body>
</html>