package cn.nwnu.android.sms.animation;



import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * һ���𽥷Ŵ���ת360�ȵĶ���
 */
public class WindowAnimation extends Animation {

	private int halfWidth;
	private int halfHeight;
	private int duration;
	
	public WindowAnimation(int duration){
		this.duration = duration;
	}
	
	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		super.applyTransformation(interpolatedTime, t);
		Matrix matrix = t.getMatrix();
		matrix.preScale(interpolatedTime, interpolatedTime); //�������ţ���ʱ��interpolatedTime��ʾ���ŵı�����interpolatedTime��ֵʱ0-1����ʼʱ��0������ʱ��1
		matrix.preRotate(interpolatedTime * 360); //������ת
		matrix.preTranslate(-halfWidth, -halfHeight); //�ı䶯������ʼλ�ã�����ɢ�����ʼ���Ƶ��м�
		matrix.postTranslate(halfWidth, halfHeight);
	}

	@Override
	public void initialize(int width, int height, int parentWidth,
			int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		this.setDuration(duration); //���ö������ŵ�ʱ��
		this.setFillAfter(true); //����Ϊtrue������������ʱ�򱣳ֶ���Ч��
		this.halfHeight = height / 2; //����������е�����
		this.halfWidth = width / 2;
		this.setInterpolator(new LinearInterpolator()); //���Զ��������ʲ��䣩
	}

}