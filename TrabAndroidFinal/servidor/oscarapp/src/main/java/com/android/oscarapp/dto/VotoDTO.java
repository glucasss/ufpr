package com.android.oscarapp.dto;

import com.android.oscarapp.vo.VotoDiretorVO;
import com.android.oscarapp.vo.VotoFilmeVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotoDTO {

	private VotoDiretorVO votoDiretorVO;
	private VotoFilmeVO votoFilmeVO;
	private int token;
	
}
