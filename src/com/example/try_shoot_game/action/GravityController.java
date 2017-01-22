package com.example.try_shoot_game.action;

class GravityController implements IGravityController{
	float origineDx;
	float origineDy;
	boolean firstExecute = true;
	MathUtil mathUtil;
	
	public GravityController() {
		// TODO Auto-generated constructor stub
		mathUtil = new MathUtil();
	}
	
	@Override
	public void execute(MovementActionInfo info) {
		// TODO Auto-generated method stub
		
		float dx = info.getDx();
		float dy = info.getDy();
		
		if(firstExecute){
			long millisTotal = info.getTotal();
			long millisDelay = info.getDelay();
			origineDx = info.getDx();
			origineDy = info.getDy();
			
			float x = millisDelay / millisTotal;
			
			float tx = origineDx * x;
			float ty = origineDy * x;	

			if(isInverseAngel){
//				float angle = mathUtil.getAngle();
//				angle += 180;
				mathUtil.inverseAngel();
//				mathUtil.setAngle(angle);
			}
			
			else if(isCyclePath){
				mathUtil.setXY(dx, dy);
				mathUtil.genAngle();
				mathUtil.cyclePath();
			}
			
			else if(isInversePath){
				mathUtil.inversePath();
			}
			
			else if(isWavePath){
				mathUtil.setXY(dx, dy);
				mathUtil.genAngle();
				mathUtil.wavePath();
			}
			
			else if(isSlopeWavePath){
				mathUtil.slopeWavePath();
			}
			
			else{
//				mathUtil = new MathUtil(dx, dy);
				mathUtil.setXY(dx, dy);
				mathUtil.genAngle();
				
			}
			
			mathUtil.initGravity();
			firstExecute = false;
		}
		

		mathUtil.genGravity();
		dx = mathUtil.getSpeedX();
		dy = mathUtil.getSpeedY();
		
		info.setDx(dx);
		info.setDy(dy);
	}

	@Override
	public void reset(MovementActionInfo info) {
		// TODO Auto-generated method stub
		info.setDx(origineDx);
		info.setDy(origineDy);
//		mathUtil = new MathUtil();
		mathUtil.reset();
		firstExecute = true;
	}

	boolean isInverseAngel = false;
	boolean isCyclePath = false;
	boolean isInversePath = false;
	boolean isWavePath = false;
	boolean isSlopeWavePath = false;
	
	@Override
	public void isInverseAngel() {
		// TODO Auto-generated method stub
		isInverseAngel = true;
	}


	@Override
	public void isCyclePath() {
		// TODO Auto-generated method stub
		this.isCyclePath = true;
	}

	@Override
	public void isInversePath() {
		// TODO Auto-generated method stub
		isInversePath = true;
	}

	@Override
	public void isWavePath() {
		// TODO Auto-generated method stub
		isWavePath = true;
	}
	@Override
	public void isSlopeWavePath() {
		// TODO Auto-generated method stub
		isSlopeWavePath = true;
	}

	@Override
	public MathUtil getMathUtil() {
		// TODO Auto-generated method stub
		return mathUtil;
	}

	@Override
	public void setMathUtil(MathUtil mathUtil) {
		// TODO Auto-generated method stub
		this.mathUtil = mathUtil;
	}

}
