package com.github.jannled.lib.math;

import com.github.jannled.lib.Print;

/**
 * A double vector.
 * @author Jannled
 */
public class Vector
{
	private double[] vals;
	
	/**
	 * Construct a new vector with zero as value.
	 * @param dimension The number of dimensions the Vector should have.
	 */
	public Vector(int dimension)
	{
		vals = new double[dimension];
	}
	
	/**
	 * Construct a new vector from the array.
	 * @param values The values to use for the new vector.
	 */
	public Vector(double[] values)
	{
		this.vals = values;
	}
	
	/**
	 * Create a Vector with three coordinates.
	 * @param x The x coordinate (left/right).
	 * @param y The y coordinate (up/down).
	 * @param z The z coordinate (outwards/inwards).
	 */
	public Vector(double x, double y, double z)
	{
		vals = new double[3];
		vals[0] = x;
		vals[1] = y;
		vals[2] = z;
	}
	
	/**
	 * Returns the addition of the two vectors.
	 * @param v The Vector to add to the first.
	 * @return The sum of the two vectors or null if the dimension of the two vectors is not equal.
	 */
	public Vector add(Vector v)
	{
		if(getValues().length != v.getValues().length)
		{
			Print.e("Failed to add Vector " + v.toString() + " to " + toString());
			return null;
		}
		else
		{
			Vector vout = new Vector(getValues().length);
			for(int i=0; i< getValues().length; i++)
			{
				vout.setValue(i, getValue(i) + v.getValue(i));
			}
			return vout;
		}
	}
	
	/**
	 * Returns the subtraction of the two vectors.
	 * @param v The Vector to subtract from the first.
	 * @return The result of the two vectors or null if the dimension of the two vectors is not equal.
	 */
	public Vector subtract(Vector v)
	{
		if(getValues().length != v.getValues().length)
		{
			Print.e("Error while subracting!");
			return null;
		}
		else
		{
			Vector vout = new Vector(getValues().length);
			for(int i=0; i< getValues().length; i++)
			{
				vout.setValue(i, getValue(i) - v.getValue(i));
			}
			return vout;
		}
	}
	
	/**
	 * Multiply the vector with the given scalar.
	 * @param scalar The scalar.
	 * @return The result of the multiplication.
	 */
	public Vector multiply(double scalar)
	{
		Vector vout = new Vector(getValues().length);
		for(int i=0; i< getValues().length; i++)
		{
			vout.setValue(i, getValue(i) * scalar);
		}
		return vout;
	}
	
	/**
	 * Calculate the cross product between two 3-dimensional vectors.
	 * @param v The second vector to multiply.
	 * @return The cross product of the two vectors.
	 */
	public Vector crossProduct(Vector v)
	{
		if(getValues().length != 3 || v.getValues().length != 3)
		{
			System.err.println("Dimension error while cross multiplying matrices.");
			return null;
		}
		
		Vector out = new Vector(
				getValue(1) * v.getValue(2) - getValue(2) * v.getValue(1),
				getValue(2) * v.getValue(0) - getValue(0) * v.getValue(2),
				getValue(0) * v.getValue(1) - getValue(1) * v.getValue(0));
		
		return out;
	}
	
	/**
	 * Calculate the dot product between the two matrices.
	 * @param v The second argument to multiply with.
	 * @return THe dot product or -1 if failed.
	 */
	public double dotProduct(Vector v)
	{
		double out = -1;
		
		if(getValues().length != v.getValues().length)
		{
			Print.e("The two vectors must have the same dimension!" + getValues().length + ", ");
			return out;
		}
		
		for(int i=0; i<v.getValues().length; i++)
		{
			out += getValue(i) * v.getValue(i);
		}
		
		return out;
	}
	
	/**
	 * Get one value from the vector. 
	 * @param index The index of the value to get (from zero to n).
	 * @return The specified value of the vector.
	 */
	public double getValue(int index)
	{
		return vals[index];
	}
	
	/**
	 * Get all values from the vector.
	 * @return The values are stored in a double array.
	 */
	public double[] getValues()
	{
		return vals;
	}
	
	/**
	 * Get the x value.
	 * @return 
	 */
	public double X()
	{
		return vals[0];
	}

	/**
	 * Get the y value.
	 * @return 
	 */
	public double Y()
	{
		return vals[1];
	}

	/**
	 * Get the z value.
	 * @return 
	 */
	public double Z()
	{
		return vals[2];
	}
	
	/**
	 * Sets a value of the vector.
	 * @param index The index of the value to set.
	 * @param value The value to set.
	 */
	public void setValue(int index, double value)
	{
		vals[index] = value;
	}
	
	@Override
	public String toString()
	{
		String output = "[";
		for(int i=0; i<vals.length; i++)
		{
			output += (i<vals.length-1) ? vals[i] + ",\t" : vals[i] + "";
		}
		return output += "]";
	}
}
