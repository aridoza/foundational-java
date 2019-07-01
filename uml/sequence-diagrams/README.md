# UML Sequence Diagrams
Where class diagrams provide a high level view of the structure of classes and their relationships, sequence diagrams provide a view into their interactions.

A sequence diagram consists of a series of parallel vertical "lifelines", representing the classes or other processes comprising the interactions of the system. Method calls or "messages" are represented as horizontal lines connecting the caller to the called object. The message line is usually annotated with the method name, and optionally the call parameters and return type.

For example:  
![](resources/synchronous-call.png)  
In this sequwence, an instance of the class TradingMain is making a call to the method `saveTradeDetails` on an instance of the class TradeDAL (DAL stands for "Data Access Layer", a common programming paradigm for making database calls.) Then the methods returns, as indicated by the return arrow.



