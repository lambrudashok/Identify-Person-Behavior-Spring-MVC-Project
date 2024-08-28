package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.PostLayoutModel;
import com.model.PredictionBehaviorModel;
import com.model.PredictionModel;
import com.repository.PredictionRepository;

@Service
public class PredictionService {

	@Autowired
	PredictionRepository predictRepo;
	
	/*view All posts particular users*/
	public List<PostLayoutModel> viewAllUserPosts(int registerid){
		return predictRepo.viewAllUserPosts(registerid);
	}
		
	// fetch single post
	public String ViewPost(int postid){
		return predictRepo.ViewPost(postid);
	}
		
	// prediction person behavior using  post
     	public PredictionBehaviorModel predictPersonBehavior(String post) {
     		PredictionBehaviorModel model = new PredictionBehaviorModel();
     		int predicted=predictRepo.predictPersonBehavior(post);
     		if(predicted==1)
    			model.setOpenessToExperience(1);
     		else if(predicted==2)
     			model.setConscientiousness(1);
     		else if(predicted==3)
     			model.setExtroversion(1);
     		else if(predicted==4)
     			model.setAgreeableness(1);
     		else
     			model.setNeuroticism(1);
     		
     		return model;
     	}
     	
     /* get All posts comments and like of posts particular user */
    	public String[] getAllPostsCommentsLikes(int predictUserID) { 
    		return predictRepo.getAllPostsCommentsLikes(predictUserID);
    	}
     	
     	
     // prediction person behavior overall
     	public PredictionBehaviorModel predictOverAllPersonBehavior(String[] unlabelledInformation){
     		List<PredictionModel> personBehavior= predictRepo.predictOverAllPersonBehavior(unlabelledInformation);
     		int openessToExperience=0,conscientiousness=0,extroversion=0,agreeableness=0,neuroticism=0;
     		
     		for(PredictionModel pm:personBehavior) { 
     			if(pm.getCluster()==1)
     				openessToExperience++;
         		else if(pm.getCluster()==2)
         			conscientiousness++;
         		else if(pm.getCluster()==3)
         			extroversion++;
         		else if(pm.getCluster()==4)
         			agreeableness++;
         		else
         			neuroticism++; 
     	    }
     		
     		PredictionBehaviorModel model = new PredictionBehaviorModel();
     		model.setOpenessToExperience(openessToExperience);
     		model.setConscientiousness(conscientiousness);
     		model.setExtroversion(extroversion);
     		model.setAgreeableness(agreeableness);
     		model.setNeuroticism(neuroticism);
     		
     		return model;
     	}
}
