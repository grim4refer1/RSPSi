package com.rspsi.plugin.loader;

import org.major.cache.anim.FrameConstants;

import com.jagex.cache.anim.Frame;
import com.jagex.cache.anim.FrameBase;
import com.jagex.cache.loader.anim.FrameBaseLoader;
import com.jagex.cache.loader.anim.FrameLoader;
import com.jagex.io.Buffer;

public class FrameLoader317 extends FrameLoader {

	private Frame[] frames;

	public void init(int size) {
		frames = new Frame[size];
	}

	@Override
	protected Frame forId(int index) {
		return null;//return (frames == null) ? null : frames[index];
	}

	@Override
	public void load(int file, byte[] data) {
		Buffer buffer = new Buffer(data);
		FrameBase base = FrameBaseLoader.instance.decode(buffer);

		int frameCount = buffer.readUShort();

		int[] translationIndices = new int[500];
		int[] transformX = new int[500];
		int[] transformY = new int[500];
		int[] transformZ = new int[500];

		for (int frameIndex = 0; frameIndex < frameCount; frameIndex++) {
			int id = buffer.readUShort();
			Frame frame = frames[id] = new Frame();
			frame.setBase(base);

			int transformations = buffer.readUByte();
			int lastIndex = -1;
			int transformation = 0;

			for (int index = 0; index < transformations; index++) {
				int attribute = buffer.readUByte();
				if (attribute > 0) {
					if (base.getTransformationType(index) != FrameConstants.CENTROID_TRANSFORMATION) {
						for (int next = index - 1; next > lastIndex; next--) {
							if (base.getTransformationType(next) != FrameConstants.CENTROID_TRANSFORMATION) {
								continue;
							}

							translationIndices[transformation] = next;
							transformX[transformation] = 0;
							transformY[transformation] = 0;
							transformZ[transformation] = 0;
							transformation++;
							break;
						}
					}

					translationIndices[transformation] = index;
					int standard = base.getTransformationType(index) == FrameConstants.SCALE_TRANSFORMATION ? 128 : 0;

					transformX[transformation] = (attribute & FrameConstants.TRANSFORM_X) != 0 ? buffer.readShort2() : standard;
					transformY[transformation] = (attribute & FrameConstants.TRANSFORM_Y) != 0 ? buffer.readShort2() : standard;
					transformZ[transformation] = (attribute & FrameConstants.TRANSFORM_Z) != 0 ? buffer.readShort2() : standard;

					lastIndex = index;
					transformation++;

					if (base.getTransformationType(index) == FrameConstants.ALPHA_TRANSFORMATION) {
						frame.setOpaque(false);
					}
				}
			}

			frame.setTransformationCount(transformation);
			frame.setTransformationIndices(translationIndices);
			frame.setTransformX(transformX);
			frame.setTransformY(transformY);
			frame.setTransformZ(transformZ);
		}
	}

}