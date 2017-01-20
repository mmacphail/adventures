# Adventures

This project was developed using the DDD framework to implement an online role-playing gaming website. It is a toy project developped under two weeks with the following philosophy:
* KISS
* Clean
* Open
* Free
* Experimental

## Technologies involved
Java EE 7 with
* JPA
* EJB
* JSF

And PicketLink for security management.

## Challenges involved

The domain was/is unknown, and that made the development really hard. Naming was difficult, being a novice business
owner I wasn't able to stick with a nomenclature. Also, I'm certain there are some hidden there, lurking, that have not
been discovered in the domain.

I should have analyzed more before hands some of the specs of JEE 7 to clean-up the design (like BeanValidation, 
Interceptors, Decorators, Producers). Decoupling cross-cutting concerns such as errors and exceptions management using
those specs would have been handy.
 
It was hard after having studied a bit of functional programing to go back to the old OO. But it was kind of fun too.
However, some habits die hard, and it was hard to ha non-final fields and to simply update my entities. It was also
hard to use JEE exception management, instead of using functional ways to deal with exceptions.

PrimeFaces was really fun to use, but JSF was a complete monster as usual, and I spent most of this project time
figuring if I had to use a link, a command link, a command button with a "button tyoe"... All the time gained by not
caring about the XHTML view was probably lost debugging some odd behaviours with parametrized URLs. Also, the
framework has some clearly questionable design and it felt wrong using it. 

PicketLink was surprisingly easy to setup and use, and that's a good thing because I'm really bad at security. I tried 
to implement Auth0 Oauth but not knowing JSF well enough it was a nightmare so I gave up.
