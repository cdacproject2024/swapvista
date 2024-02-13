package com.swapvista.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

//super class for common  api response fields 
public class CommonApiResponse {
	// Response message indicating the result of the API call
	private String responseMessage;
	// Flag indicating whether the API call was successful or not
	private boolean isSuccess;

}
