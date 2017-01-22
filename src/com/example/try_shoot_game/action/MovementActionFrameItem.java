package com.example.try_shoot_game.action;

import java.util.ArrayList;
import java.util.List;

import android.os.CountDownTimer;
import android.os.Looper;
import android.util.Log;

public class MovementActionFrameItem extends MovementAction{
	CountDownTimer countDownTimer; 
	long millisTotal;
	long millisDelay;
	float dx;
	float dy;
	MovementActionInfo info;
	long resumeTotal;
	long resetTotal;
	IRotationController rotationController;
	IGravityController gravityController;
	
	public MovementActionFrameItem(long millisTotal, long millisDelay, final int dx, final int dy){
		this(millisTotal, millisDelay, dx, dy, "MovementItem");
//		description = "MovementItem";
	}
	
	public MovementActionFrameItem(long millisTotal, long millisDelay, final int dx, final int dy, String description){
		this.millisTotal = millisTotal;
		this.millisDelay = millisDelay;
		this.dx = dx;
		this.dy = dy;
		info = new MovementActionInfo(millisTotal, millisDelay, dx, dy);
		this.description = description + ",";
		movementItemList.add(this);
	}
	
	public MovementActionFrameItem(MovementActionFrameInfo info){
		frameTimes = info.getFrame();
//		millisTotal = info.getTotal();
//		millisDelay = info.getDelay();
		dx = info.getDx();
		dy = info.getDy();
		if(info.getDescription()!=null)
			this.description = info.getDescription() + ",";
		this.info = info;
		movementItemList.add(this);
	}
	
	public MovementActionFrameItem(long[] frameTimes, final int dx, final int dy, String description){
		this.frameTimes = frameTimes;
		this.dx = dx;
		this.dy = dy;
		info = new MovementActionInfo(millisTotal, millisDelay, dx, dy);
		this.description = description + ",";
		movementItemList.add(this);
	}
	
	@Override
	public void setTimer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

		frameStart();
		
//		countDownTimer.start();
	}
	
	long[] frameTimes;
	int resumeFrameIndex;
	int resumeFrameCount;
	
	public interface FrameTrigger{
		public void trigger();
	}
	
	private void nextFrameTrigger(){
//		frameStart();
		myTrigger.trigger();
		nextframeTrigger.trigger();
	}
	
	FrameTrigger nextframeTrigger;
	FrameTrigger myTrigger = new FrameTrigger() {
		
		@Override
		public void trigger() {
			// TODO Auto-generated method stub
			frameStart();
			
		}
	};
	public FrameTrigger setNextFrameTrigger(FrameTrigger nextframeTrigger){
		
		this.nextframeTrigger = nextframeTrigger;
		
		return myTrigger;
	}
	
	private void frameStart(){
		Thread thread = new Thread(new Runnable() {
			IActionListener actionListener = null;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				for(; resumeFrameIndex < frameTimes.length; resumeFrameIndex++){
					actionListener.beforeChangeFrame(resumeFrameIndex+1);
//					myTrigger.trigger();
					for(; resumeFrameCount < frameTimes[resumeFrameIndex]; resumeFrameCount++){
						try {
							Thread.sleep(frameTimes[resumeFrameIndex]);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					doRotation();
					doGravity();
					timerOnTickListener.onTick(dx, dy);
//					actionListener.afterChangeFrame(periousId);
					resumeFrameCount = 0;
				}
				
				doReset();
				actionListener.actionFinish();
			}
		});
		
		thread.start();
		
	}
	
	private void frameTriggerStart(){
		IActionListener actionListener = null;
		if (!isStop) {
			actionListener.beforeChangeFrame(resumeFrameIndex+1);
			if(resumeFrameIndex==0)
				resumeFrameIndex++;
			if(resumeFrameCount>frameTimes[resumeFrameIndex]){	
				resumeFrameIndex++;
				resumeFrameIndex %= frameTimes.length;
				resumeFrameCount = 0;
			}
			
			if(!isLoop && resumeFrameIndex==0){
				isStop = true;
				doReset();
				actionListener.actionFinish();
			}else if(resumeFrameCount==frameTimes[resumeFrameIndex]){
				updateTime = System.currentTimeMillis() + frameTimes[resumeFrameIndex];	
				doRotation();
				doGravity();
				timerOnTickListener.onTick(dx, dy);		
				int periousId = resumeFrameIndex-1<0 ? frameTimes.length+(resumeFrameIndex-1) : resumeFrameIndex-1;
				actionListener.afterChangeFrame(periousId);
			}
			resumeFrameCount++;
//			if(resumeFrameCount==frameTimes[resumeFrameIndex])
//				resumeFrameCount = 0;
		}
		
		doReset();
	}
	
	public boolean isLoop;
	
	public String name;
	
	private long updateTime;
	
	public int frameIdx;
	
	public boolean isStop = false;
	
	private void irregularFrameStart(){
		
		IActionListener actionListener = null;
		
//		for(; resumeFrameIndex < frameTimes.length; resumeFrameIndex++){
			if (System.currentTimeMillis() > updateTime && !isStop) {
				actionListener.beforeChangeFrame(frameIdx+1);
				frameIdx++;
				frameIdx %= frameTimes.length;
				
				if(!isLoop && frameIdx==0){
					isStop = true;
					doReset();
					actionListener.actionFinish();
				}else{
//					bitmap = bitmapFrames[frameIdx];
					updateTime = System.currentTimeMillis() + frameTimes[frameIdx];
					
//					int w = bitmap.getWidth();
//					int h = bitmap.getHeight();
//					
//					setWidth(bitmap.getWidth());
//					setHeight(bitmap.getHeight());
					
					doRotation();
					doGravity();
					timerOnTickListener.onTick(dx, dy);
					
					int periousId = frameIdx-1<0 ? frameTimes.length+(frameIdx-1) : frameIdx-1;
					actionListener.afterChangeFrame(periousId);
				}
//			}

		}
		
		
	}
	
	@Override
	protected MovementAction initTimer(){
		millisTotal = info.getTotal();
		millisDelay = info.getDelay();
		dx = info.getDx();
		dy = info.getDy();
		rotationController = info.getRotationController();
		gravityController = info.getGravityController();
		
		resumeFrameIndex = 0;
		
		countDownTimer = new CountDownTimer(millisTotal,
				millisDelay) {

			@Override
			public void onTick(long millisUntilFinished) {
				Log.e("t", millisUntilFinished+"");
				Log.e("t", millisUntilFinished/1000+"");
				// TODO Auto-generated method stub
				float x = dx;
				float y = dy;
				
				doRotation();
				doGravity();
				Log.e("dx", dx+"");
				Log.e("dy", dy+"");
				
				resumeTotal = millisUntilFinished;
				timerOnTickListener.onTick(dx, dy);
			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				synchronized (MovementActionFrameItem.this) {
					MovementActionFrameItem.this.notifyAll();
				}			
				doReset();
			}
		};
		return this;
	}
	
	private void doRotation(){
//		dx = dx * 1.1f;
		if(rotationController!=null){
			rotationController.execute(info);
			dx = info.getDx();
			dy = info.getDy();
		}
	}
	
	private void doGravity(){
//		dx = dx * 1.1f;
		if(gravityController!=null){
			gravityController.execute(info);
			dx = info.getDx();
			dy = info.getDy();
		}
	}
	
	private void doReset(){
		if(gravityController!=null){
			gravityController.reset(info);
		}
		if(rotationController!=null)
			rotationController.reset(info);


		millisTotal = info.getTotal();
		millisDelay = info.getDelay();
		dx = info.getDx();
		dy = info.getDy();
//		rotationController = info.getRotationController();
	}
	
	@Override
	public MovementAction getAction(){
		return this;
	}
	
	public List<MovementAction> getActions(){
		return actions;
	}

	@Override
	public MovementActionInfo getInfo() {
		// TODO Auto-generated method stub
		return info;
	}

	@Override
	public void setInfo(MovementActionInfo info) {
		// TODO Auto-generated method stub
		this.info = info;
	}
	
	@Override
	public List<MovementAction> getCurrentActionList() {
		// TODO Auto-generated method stub
		List<MovementAction> actions = new ArrayList<MovementAction>();
		actions.add(this);
		return actions;
	}
	
	@Override
	public List<MovementActionInfo> getCurrentInfoList() {
		// TODO Auto-generated method stub
		List<MovementActionInfo> infos = new ArrayList<MovementActionInfo>();
		infos.add(this.info);
		return infos;
	}
	
	@Override
	public List<MovementActionInfo> getMovementInfoList() {
		List<MovementActionInfo> infos = new ArrayList<MovementActionInfo>();
		infos.add(this.info);
		return infos;
	}
	
	@Override
	public void cancelMove(){
//		for(MovementAction action : this.getAction().actions){
//			action.cancelMove();
//		}
		countDownTimer.cancel();
	}
	
	@Override
	void pause(){
		countDownTimer.cancel();
		
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resetTotal = millisTotal;
		millisTotal =resumeTotal;
		info.setTotal(millisTotal);
		initTimer();
		start();
	}
}