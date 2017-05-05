Proposition for the Redmart 
["1,000,000th Customer Prize - another programming challenge"](http://geeks.redmart.com/2015/10/26/1000000th-customer-prize-another-programming-challenge/). 

## Explainations

The first approach used to attempt to solve the problem is the greedy approach, 
which tries to pick the products with the highest value / volume ratio. 
However, this approach proves not to be optimal (see tests for this strategy). 
 
An optimial solution can be found using *dynamic programming*. This approach is, however, 
costly in term of consumed memory. 

To solve the exercice I put the restriction of having only a single 
instance of a given product in the tote. 

About the "max 1 instance of the product" rule: 
 * as a Customer I'd rather pick several times the same product as I only have 1 hour to fill my Tote
 * still, one hour should be enough to go around the warehouse
 * still, I could even try to pre-order my items on Redmart and pickup the Tote already prepared (nasty, isn't it?)
 * allowing multiple instance of a same product:
    * makes the greedy algorithm to generally return better results
    * the 0/1 knapsack dynamic programming solution is not necessary the optimal one anymore

The project is a Spring Boot application. 
This was not necessary in this project, but overall it helps to achieve decoupling and configuration in general. 

## Run the project

In the project direcotry, run (Linux): 

```bash
mvn clean package && java -Xmx6g -jar target/million-tote-0.0.1-SNAPSHOT.jar
```