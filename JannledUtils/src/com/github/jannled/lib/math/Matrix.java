package com.github.jannled.lib.math;

/**
 * A float matrix.
 * @author Jannled
 */
public class Matrix
{
	private float[] values;
	private int width;
	private int height;
	
	/**
	 * Create an empty matrix with the given dimensions.
	 * @param width The width of the matrix.
	 * @param height The height of the matrix.
	 */
	public Matrix(int width, int height)
	{
		values = new float[width * height];
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Create a matrix with the given values, they are stored as a one dimensional array, the with and height of the matrix are stored in seperate values.
	 * @param values The values to initialize the matrix with.
	 * @param width The width of the matrix.
	 * @param height The height of the matrix.
	 */
	public Matrix(float[] values, int width, int height)
	{
		this.values = values;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Not recommend because the vector is stored in a one dimensional array, so the two dimensional vector needs to be transformed. 
	 * @param values The values for the matrix
	 */
	public Matrix(float[][] values)
	{
		width = values.length;
		height = values[0].length;
		
	}
	
	/**
	 * Create a new identity matrix.
	 * @param width The width of the identity matrix.
	 * @param height The height of the identity matrix.
	 * @return The identity matrix.
	 */
	public static Matrix identity(int width, int height)
	{
		Matrix mout = new Matrix(width, height);
		int count = (width < height) ? height : width;
		for(int i=0; i<count; i++)
		{
			mout.set(i, i, 1);
		}
		return mout;
	}
	
	/**
	 * Multiply the matrix with the given one.
	 * @param matrix The matrix to multiply with.
	 * @return The result matrix, or null if the size of the matrices is not equals.
	 */
	public Matrix multiply(Matrix matrix)
	{
		int width = getDimension()[0];
		int height = getDimension()[1];
		
		if(!(matrix.getDimension()[0] == width && matrix.getDimension()[1] == height))
		{
			return null;
		}
		Matrix result = new Matrix(width, height);
		
		for(int y=0; y < height; y++) {
			for(int x=0; x < 4; x++) {
				float sum = 0.0f;
				for(int e=0; e < width; e++) {
					sum += this.values[e + y * height] * matrix.values[x + e * width];
				}
				result.values[x + y * 4] = sum;
			}
		}
		return result;
	}
	
	/**
	 * Add the matrix to the given one.
	 * @param matrix The matrix to add to
	 * @return The result matrix, or null if the size of the matrices is not equals
	 */
	public Matrix add(Matrix matrix)
	{
		if(!(matrix.getDimension()[0] == width && matrix.getDimension()[1] == height))
		{
			return null;
		}
		
		float[] result = new float[values.length];
		
		for(int i=0; i<values.length; i++)
		{
			result[i] = getValues()[i] + matrix.getValues()[i]; 
		}
		
		return new Matrix(result, width, height);
	}
	
	/**
	 * Subtract the matrix from the given one.
	 * @param matrix The subtrahend matrix
	 * @return The result matrix, or null if the size of the matrices is not equals
	 */
	public Matrix subtract(Matrix matrix)
	{
		if(!(matrix.getDimension()[0] == width && matrix.getDimension()[1] == height))
		{
			return null;
		}
		
		float[] result = new float[values.length];
		
		for(int i=0; i<values.length; i++)
		{
			result[i] = getValues()[i] - matrix.getValues()[i];
		}
		
		return new Matrix(result, width, height);
	}
	
	public float get(int xpos, int ypos)
	{
		return values[xpos * ypos];
	}
	
	public float[] getValues()
	{
		return values;
	}

	/**
	 * Get the dimension of the matrix
	 * @return The first value is the width, the second value is the height. 
	 */
	public int[] getDimension()
	{
		return new int[] {width, height};
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * Set a value in the matrix.
	 * @param xpos The x position of the value to alter.
	 * @param ypos The y position of the value to alter.
	 * @param value The value to alter.
	 */
	public void set(int xpos, int ypos, float value)
	{
		values[xpos * ypos] = value;
	}
	
	@Override
	public String toString()
	{
		String output = "";
		
		for(int y=0; y<height; y++)
		{
			output += "[";
			for(int x=0; x<width; x++)
			{
				output += (x<width-1) ? values[x*y] + ",\t" : values[x*y] + "";
			}
			output += "] \n";
		}
		
		return output;
	}
}