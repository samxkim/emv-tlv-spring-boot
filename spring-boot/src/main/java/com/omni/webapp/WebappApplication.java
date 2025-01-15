package com.omni.webapp;

import com.omni.webapp.models.InitialEMVTags;
import com.omni.webapp.models.Tag;
import com.omni.webapp.models.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WebappApplication {

	public static void main(String[] args) {

		SpringApplication.run(WebappApplication.class, args);
	}

}
