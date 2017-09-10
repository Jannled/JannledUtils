package com.github.jannled.lib.math;

import java.io.Serializable;
import java.util.Arrays;

import com.github.jannled.lib.Print;

/**
 * A double matrix.
 * @author Jannled
 */
public class Matrix implements Serializable
{
	private static final long serialVersionUID = 6881648817538891334L;
	private double[] values;
	private int width;
	private int height;
	
	/**
	 * Create an empty matrix with the given dimensions.
	 * @param width The width of the matrix.
	 * @param height The height of the matrix.
	 */
	public Matrix(int width, int height)
	{
		values = new double[width * height];
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Create a matrix with the given values, they are stored as a one dimensional array, the with and height of the matrix are stored in seperate values.
	 * @param values The values to initialize the matrix with.
	 * @param width The width of the matrix.
	 * @param height The height of the matrix.
	 */
	public Matrix(double[] values, int width, int height)
	{
		this.values = values;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Create a matrix filled with a specific number.
	 * @param value The number to fill the matrix with.
	 * @param width The width of the matrix.
	 * @param height The height of the matrix.
	 */
	public Matrix(double value, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.values = new double[width*height];
		Arrays.fill(values, value);
	}
	
	/**
	 * Not recommend because the vector is stored in a one dimensional array, so the two dimensional vector needs to be transformed. 
	 * @param values The values for the matrix, row major ordered.
	 */
	public Matrix(double[][] values)
	{
		width = values[0].length;
		height = values.length;
		
		this.values = new double[width*height];
		
		for(int i=0; i<height; i++)
		{
			if(values[i] != null)
				System.arraycopy(values[i], 0, this.values, i*width, width);
		}
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
	 * @return The result matrix, or null if the matrix cannot be multiplied.
	 */
	public Matrix multiply(Matrix matrix)
	{
		int width = matrix.getWidth();
		int height = this.getHeight();
		Matrix out = new Matrix(width, height);

		if(this.getWidth() != matrix.getHeight())
		{
			Print.e("Failed to multiply matrix of size " + getWidth() + "x"+ getHeight() + " with " + matrix.getWidth() + "x" + matrix.getHeight() + ". The width of matrix A must be equals the height of B!");
			return null;
		}
		
		for(int c=0; c<width*height; c++)
		{
			double sum = 0;
			for(int e=0; e<getWidth(); e++)
			{
				int apos = (c/(width))*getWidth() + e;
				int bpos = (c%width) + e*matrix.getWidth();
				sum = sum + this.getValues()[apos] * matrix.getValues()[bpos];
			}
			out.getValues()[c] = sum;
		}
		
		return out;
	}
	
	/**
	 * Multiply each element of the matrix with the given value.
	 * @param value The factor.
	 * @return The result.
	 */
	public Matrix multiply(double value)
	{
		Matrix out = new Matrix(getWidth(), getHeight());
		for(int i=0; i<getWidth()*getHeight(); i++)
		{
			out.set(1, getValues()[i] * value);
		}
		return out;
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
			Print.e("Failed to add matrix of size " + getWidth() + "x"+ getHeight() + " to " + matrix.getWidth() + "x" + matrix.getHeight() + ". The width of matrix A must be equals the height of B!");
			return null;
		}
		
		double[] result = new double[values.length];
		
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
			Print.e("Failed to subtract matrix of size " + getWidth() + "x"+ getHeight() + " by " + matrix.getWidth() + "x" + matrix.getHeight() + ". The width of matrix A must be equals the height of B!");
			return null;
		}
		
		double[] result = new double[values.length];
		
		for(int i=0; i<values.length; i++)
		{
			result[i] = getValues()[i] - matrix.getValues()[i];
		}
		
		return new Matrix(result, width, height);
	}
	
	/**
	 * Transpose the current matrix.
	 * @return The transposed matrix.
	 */
	public Matrix transpose()
	{
		Matrix out = new Matrix(getHeight(), getWidth());
		for(int i=0; i<getWidth()*getHeight(); i++)
		{
			int row = (i%height) * width + i/height;
			out.getValues()[i] = getValues()[row];
		}
		return out;
	}
	
	/**
	 * Get a single value from the matrix.
	 * @param xpos The X-position of the value.
	 * @param ypos The Y-position of the value.
	 * @return The value stored in that position.
	 */
	public double get(int xpos, int ypos)
	{
		return values[ypos*width+xpos];
	}
	
	/**
	 * Get all values in a two dimensional array. 
	 * most efficient method for getting values, because the values are stored in a single-dimensional array. You might need
	 * additional informations (width and height of the matrix) to read it properly.
	 * @return All values in a double array. 
	 */
	public double[] getValues()
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
	
	/**
	 * Getter for the width of the matrix.
	 * @return Number of columns.
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Getter for the height of the matrix.
	 * @return Number of rows.
	 */
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
	public void set(int xpos, int ypos, double value)
	{
		values[ypos*width+xpos] = value;
	}
	
	public void set(int pos, double value)
	{
		values[pos] = value;
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
				output += (x<width-1) ? Maths.round(values[y*width+x], 3) + ",\t" : Maths.round(values[y*width+x], 3) + "";
			}
			output += "] \n";
		}
		
		return output;
	}
}