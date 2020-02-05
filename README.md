# arrayflat
app developed with jdk8 and maven 3
the app will flatten an array of arbitrarily nested arrays of integers into a flat array of integers. e.g. [[1,2,[3]],4] -> [1,2,3,4].

# For test:
  execute: mvn test

# For run:
  execute: mvn install  
  move to: in folder /target  
  execute: java -jar arrayflat-0.0.1-SNAPSHOT.jar "[[1,2,[3]],4]"
  
  
