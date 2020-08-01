package com.example.oscarapp.dto;

import com.example.oscarapp.VO.VotoDiretorVO;
import com.example.oscarapp.VO.VotoFilmeVO;

public class VotoDTO {

    private VotoDiretorVO votoDiretorVO;
    private VotoFilmeVO votoFilmeVO;
    private int token;

    public VotoDiretorVO getVotoDiretorVO(){
        return this.votoDiretorVO;
    }

    public void setVotoDiretorVO(VotoDiretorVO i){
        this.votoDiretorVO = i;
    }

    public VotoFilmeVO getVotoFilmeVO(){
        return this.votoFilmeVO;
    }

    public void setVotoFilmeVO(VotoFilmeVO v){
        this.votoFilmeVO = v;
    }

    public int getToken(){
        return this.token;
    }

    public void setToken(int token){
        this.token = token;
    }

}
