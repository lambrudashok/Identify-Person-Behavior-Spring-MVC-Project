<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*,com.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/CSS/userHomeStyle.css">
<script type="text/javascript" src="resources/JS/homevalidation.js"></script>
</head>
<body>


<div class="main">

	<div class="leftMenuDiv">
	<%@ include file="menus.jsp" %> 
	</div><!-- leftMenuDiv -->
	
<!-- ------------------------------------------------------------------------------------------------------------------------- -->	
	<div class="middleSlideDiv">

	   <ul class="nav nav-pills nav-fill gap-2 p-1 small bg-none shadow-sm sticky-top mobilepill" id="pillNav2" role="tablist" style="--bs-nav-link-color: var(--bs-white); --bs-nav-pills-link-active-color: var(--bs-black); --bs-nav-pills-link-active-bg: var(--bs-white);">
  	   <li class="nav-item" role="presentation">
       <button class="nav-link active rounded-5" id="foryou-tab" data-bs-toggle="tab" onclick="showmiddlecontent(this)" data-bs-target="#foryou-content" type="button" role="tab" aria-controls="foryou-content" aria-selected="true">For You</button>
       </li>
       <li class="nav-item" role="presentation">
       <button class="nav-link rounded-5" id="following-tab" data-bs-toggle="tab"  onclick="showmiddlecontent(this)" data-bs-target="#following-content" type="button" role="tab" aria-controls="following-content" aria-selected="false">Following</button>
       </li>
       <li class="nav-item" id="mobilepillfollowsearch" role="presentation">
       <button class="nav-link rounded-5" id="searching-tab" data-bs-toggle="tab" onclick="mobilesearchtabfun(this)" type="button" >Search</button>
       </li>
       </ul>
       
		
	   <ul class="nav nav-pills nav-fill gap-2 p-1 small bg-none shadow-sm sticky-top mobilepill" id="pillNv" role="tablist" style="--bs-nav-link-color: var(--bs-white); --bs-nav-pills-link-active-color: var(--bs-black); --bs-nav-pills-link-active-bg: var(--bs-white);">
  	   <li class="nav-item" role="presentation">
       <button class="nav-link active rounded-5" id="foryou-tab" data-bs-toggle="tab" data-bs-target="#foryou-content" type="button" role="tab" aria-controls="foryou-content" aria-selected="true">For You</button>
       </li>
       <li class="nav-item" role="presentation">
       <button class="nav-link rounded-5" id="following-tab" data-bs-toggle="tab" data-bs-target="#following-content" type="button" role="tab" aria-controls="following-content" aria-selected="false">Following</button>
       </li>
       </ul>

       <div class="tab-content" id="MainMiddleContent">
       
       		<!-- for you Content -->
       		<div class="tab-pane fade show active" id="foryou-content" role="tabpanel" aria-labelledby="foryou-tab">
       		<!-- foryou-------------------------------------------------------------------------------------------------------------------------- -->
       		
       		<div class="allapplicationposts">
       		 
       		<%
       		List<PostLayoutModel> postlist=(List<PostLayoutModel>) request.getAttribute("postlist");
	            if(postlist!=null){
	                for(PostLayoutModel post:postlist){
	                	%>
	                	<div class="proA">
	                	<div class="userlogoA">
	                    <img alt="" src="resources/Profile_Images/<%=post.getProfileimage() %>" width="100px" height="50px">
	                    </div>
	                    
	                    <div class="userpostA">
	                    	<div id="userPA">
	                   		<h4 id="h4"><%=post.getUsername() %></h4>
	                   		 </div>
	                    <div id="postdisplayA"><%=post.getPost() %></div>
	                    <img alt="" src="resources/Post_Images/<%=post.getImgname()%>">
	                    
	                    
		                    <div id="likecommentdivA">
		                    
		                     <div class="likesp">
		                    
		                    <div id="likecommentGridA<%= post.getPostid() %>">	                    
		                     <%
				                 int v=post.getLike();
								 if(v>0){
									 %>			 
					                    <a id="liked" onclick="unlikeForYou(<%=post.getPostid() %>)"> <i class="fa-solid fa-heart"></i> <%=post.getLikeCount() %></a>
					                  <%
								 }else{
									 %>
									<a id="like"  onclick="likeForYou(<%=post.getPostid() %>)"> <i class="fa-solid fa-heart"></i> <%=post.getLikeCount() %></a>
			 	                    <%
								 }
				                 
				                 %> 
				                     </div> 
				                    
				                 </div>
		                    
		                    
		                    <div class="commentsp" id="commentGridA<%=post.getPostid() %>">
		                    <a id="commentshow" href="viewhomepagecomment?postid=<%=post.getPostid()%>&userID=<%=post.getRegisterid()%>"> <i class="fa-solid fa-comment"></i> <%=post.getCommentCount() %></a>
		                    <form name="frm" method="POST" onsubmit="return commentfun(<%=post.getPostid() %>,comment.value)"> 
		                    <input type="text" name="comment" id="comment" placeholder="comment here..." required> 
		                    <button type="submit" id="commentbtn"  name="commentbtn" >post</button>
		                    </form>
		                    </div>
		                    		             		                    
		                    </div><!--  likecommentdivA -->
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
       		
       		</div> <!-- allapplicationposts -->
       		
       		
       		
       		 <!-- for you ---------------------------------------------------------------------------------------------------------------------------------------- -->
       		</div><!-- for you Content -->
       
       
       
       
       
       
       
       		<!-- Following Content -->
       		<div class="tab-pane fade" id="following-content" role="tabpanel" aria-labelledby="following-tab">
       		<div class="allposts">
       		<%
       		
       		List<PostLayoutModel> listPosts =(List<PostLayoutModel>) request.getAttribute("listPosts");  // fetch following users details
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
				                 int v = posts.getLike();
								 if(v>0){
									 %>			 
					                    <a id="liked" onclick="unlikeForYou(<%=posts.getPostid() %>)"> <i class="fa-solid fa-heart"></i> <%=posts.getLikeCount() %></a>
					                  <%
								 }else{
									 %>
									<a id="like"  onclick="likeForYou(<%=posts.getPostid() %>)"> <i class="fa-solid fa-heart"></i> <%=posts.getLikeCount() %></a>
			 	                    <%
								 }
				                 
				                 %> 
				                     </div> 
				                    
				                 </div>
				                 
				                 
				            <div class="commentsp" id="commentGrid<%=posts.getPostid() %>">
		                    <a id="commentshow" href="viewhomepagecomment?postid=<%=posts.getPostid()%>&userID=<%=posts.getRegisterid()%>"> <i class="fa-solid fa-comment"></i> <%=posts.getCommentCount() %></a>
		                    <form name="frm" method="POST" onsubmit="return commentfun(<%=posts.getPostid() %>,comment.value)"> 
		                    <input type="text" name="comment" id="comment" placeholder="comment here..." required> 
		                    <button type="submit" id="commentbtn"  name="commentbtn" >post</button>
		                    </form>
		                    </div>
				                 				
		                    </div>  <!-- likecommentdiv -->
	                    </div>
	                    </div>
	                    <%
	                    }
          				}else{%><h5>Posts Not Found</h5><%}
			  			%> 
       		 </div> <!-- allposts -->  		
       		</div> <!-- Following Content -->
       
              
       </div>  <!-- MainMiddleContent -->

	</div><!-- middleSlideDiv -->
	
<!-- ------------------------------------------------------------------------------------------------------------------------- -->	
	
	<div class="rightSearchDiv" id="rightSearchDiv">
	
	<div class="searching" >
	
	   <div class="searchbar">
	   	<input type="search" id="searchuser" value="" placeholder="Search" onkeyup="searchUsingAjaxUser(this.value)">
	   </div>
	   
	   <div class="userInfo">
	   	
	    <div id="showGrid">
	  
	   	<%
			List <UserInfoModel> list =(List <UserInfoModel>) request.getAttribute("list");
			
			if(list!=null){
				
				for(UserInfoModel userInfo:list){
					%>
					
					<div class="userfollowing">
					
						<div class="photo">
							<img alt="" src="resources/Profile_Images/<%=userInfo.getProfileimage()%>">
						</div> <!-- photo -->
						
						<div class="userdetails">
							<div class="namediv">
								<div id="name"><%=userInfo.getName() %></div>
								<div id="username"><%=userInfo.getUsername() %></div>
							</div>
							<div id="btndiv">
								<%
								if(userInfo.getStatus()==0){
									%>
									<!--  follow btn-->
									<button name="follow" id="follow" value="<%=userInfo.getRegisterid()%>" onclick="followUser(this.value)" >Follow</button>            
									<%
								}else{
									%>
									 <!-- following btn -->
									<button name="following" id="following" value="<%=userInfo.getRegisterid()%>" onmouseover="unfollowShow(this)" onmouseleave="followingShow(this)" onclick="unfollowUser(this.value)" >Following</button>            
									<%
								}
								
								%>							
							</div>
						</div> <!-- userdetails -->
				
					</div> <!-- userfollowing -->
					
					<%
				}
				
			}else{
				%>
				<h4>User Not Found</h4>
				<%
			}
			
			%>
	   	
	   	</div>
	   	
	   </div> <!-- userInfo -->
	   
	</div><!-- searching -->
	
	</div> <!-- rightSearchDiv -->
	
</div> <!-- main -->
</body>
</html>