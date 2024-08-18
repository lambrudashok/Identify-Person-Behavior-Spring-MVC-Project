<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*,com.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<link rel="stylesheet" href="resources/CSS/anotherprofileStyle.css"> 
<script type="text/javascript" src='resources/JS/commentpostvalidation.js'></script> 
</head>
<body>
<%!int registerid; %>
<div class="profilecontainer">

	<div class="menuprofile">
	<%@ include file="menus.jsp" %>
	</div><!-- menuprofile -->
	
	
	<div class="userprofile">
		
		<%
			List<ProfileInformationModel> profileList=(List<ProfileInformationModel>)request.getAttribute("profileList");
			for(ProfileInformationModel prof :profileList){
			%>
		
		<div class="profilemain">
			<div class="profilelogo">
			<img alt="" src="resources/Profile_Images/<%=prof.getProfilephoto()%>">
			</div>
			<div class="profilename">
			
				<div class="profileUsername"><%=prof.getUsername() %></div>
				<div class="pff">
				<a href=""><%=prof.getPostCount()%> Posts</a>
				
				<a href=""><%=prof.getFollowingCount()%> Following</a>
				<a href=""><%=prof.getFollowerCount()%> Followers</a>
				
				</div>
				
				<div id="prodiv1">
				<div id="prodiv2">
					<div class="profileNam"><%=prof.getCustomername()%></div>
					<div class="bio"><%=prof.getBio()%></div>
				</div>
				
				
				
				<div id="prodiv3">
				
				<%
				
				int status=Integer.parseInt(request.getAttribute("status").toString());
				registerid = Integer.parseInt(request.getAttribute("registerid").toString());
				
				if(status==0){
					%>
					<!--  follow btn-->
					<button name="follow" id="follow" value="<%=registerid%>" onclick="followUser(this.value)" >Follow</button>            
					<%
				}else{
					%>
					 <!-- following btn -->
					<button name="following" id="following" value="<%=registerid%>" onmouseover="unfollowShow(this)" onmouseleave="followingShow(this)" onclick="unfollowUser(this.value)" >Following</button>            
					<%
				}
				
				%>	
				
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
			            List<PostLayoutModel> listPosts =(List<PostLayoutModel>) request.getAttribute("listPosts");
			            if(listPosts!=null){
			                for(PostLayoutModel posts:listPosts){
			                	%>
			                	<div class="pro">
			                	<div class="userlogo">
			                    <img alt="" src="resources/Profile_Images/<%=posts.getProfileimage() %>" width="100px" height="50px">
			                    </div>
			                    
			                    <div class="userpost">
			                    	<div id="userP">
			                   		 <h4><%=posts.getUsername() %></h4> 
			                   		 </div>
			                    <div id="postdisplay"><%=posts.getPost() %></div>
			                    <img alt="" src="resources/Post_Images/<%=posts.getImgname()%>">
				                    <div id="likecommentdiv">
				                    
				                   <div class="likesp">
		                    
		                    <div id="likecommentGrid<%= posts.getPostid() %>">
		                 		                    
		                     <%
				                 int v= posts.getLike();
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
				                    
				                    <div class="commentsp">
				                    <a id="commentshow" href=""> <i class="fa-solid fa-comment"></i> <%=posts.getCommentCount() %></a> 
					                    <form name="frm" action="anotheruserprofile?id=<%=registerid %>" method="post"  > 
					                    <input type="text" name="comment" id="comment" placeholder="comment here..." required> 
					                    <button type="submit" id="commentbtn"  name="btn" value="<%=posts.getPostid() %>" >post</button>
					                    </form>
					                    </div>
					                    
					                    
				                    </div>  <!-- likecommentdiv -->
			                    </div>
			                    </div>
			                    <%
			                    }
            				}else{
            					%>
            					<h5>Posts Not Founds</h5>
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