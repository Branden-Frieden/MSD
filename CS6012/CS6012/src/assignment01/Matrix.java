package assignment01;

public class Matrix {
  int numRows;
  int numColumns;
  int data[][];

  // Constructor with data for new matrix (automatically determines dimensions)
  public Matrix(int data[][]) {
    numRows = data.length; // d.length is the number of 1D arrays in the 2D array
    if (numRows == 0) {
      numColumns = 0;
    } else {
      numColumns = data[0].length; // d[0] is the first 1D array
    }
    this.data = new int[numRows][numColumns]; // create a new matrix to hold the data
    // copy the data over
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        this.data[i][j] = data[i][j];
      }
    }
  }

  @Override // instruct the compiler that we do indeed intend for this method to override
            // the superclass' (Object) version
  public boolean equals(Object other) {
    if (!(other instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
      return false;
    }
    Matrix matrix = (Matrix) other; // if the above was not true, we know it's safe to treat 'o' as a Matrix

    // check that the arrays are the same size
    if(matrix.numRows != this.numRows || matrix.numColumns != this.numColumns){
      return false;
    }

    for(int i = 0; i < numRows; i++){
      for(int j = 0; j < numColumns; j++){

        // check if each spot in the matrix is equal, if not, then return false
        if( matrix.data[i][j] != this.data[i][j]){
          return false;
        }
      }
    }
    // if there is no mismatch in the arrays, its equal
    return true;
  }

  @Override // instruct the compiler that we do indeed intend for this method to override
            // the superclass' (Object) version
  public String toString() {

    String output = "";
    for(int i = 0; i < numRows; i++){
      for(int j = 0; j < numColumns; j++){

        // loop through the rows, and print the value at each column
       output += String.valueOf(data[i][j]) + " ";
      }
      //at the end of each row, print a new lin
      output += "\n";
    }
    return output; // placeholder
  }

  public Matrix times(Matrix matrix) {

    // check that the matrix dimensions are acceptable
    if(this.numColumns != matrix.numRows){
      return null;
    }

    // create the output array of the proper size
    int[][] data = new int[this.numRows][matrix.numColumns];

    for(int i = 0; i < this.numRows; i++){
      for(int j = 0; j < matrix.numColumns; j++){
        for(int k = 0; k < matrix.numRows; k++){
            data[i][j] += this.data[i][k] * matrix.data[k][j];
        }
      }
    }

    return new Matrix(data);
  }

  public Matrix plus(Matrix matrix) {

    // check that the matrices are the proper size
    if( this.numColumns != matrix.numColumns || this.numRows != matrix.numRows){
      return null;
    }

    // create output array of the given size
    int[][] data = new int[this.numRows][this.numColumns];

    for(int i = 0; i < this.numRows; i++){
      for(int j = 0; j < this.numColumns; j++){
        // add each element of the 2 arrays and put it at the corresponding location of the output array
        data[i][j] = this.data[i][j] + matrix.data[i][j];
      }
    }

    return new Matrix( data );
  }
}
