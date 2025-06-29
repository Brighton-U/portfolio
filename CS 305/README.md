> Briefly summarize your client, Artemis Financial, and its software requirements. Who was the client? What issue did the company want you to address?

Artemis Financial is a consulting company that specializes in developing individualized financial plans, including savings, retirement, investment, and insurance plans, for its clients. The company intended to modernize its software security systems for their applications and outsourced to our employer, Global Rain.

> What did you do well when you found your client’s software security vulnerabilities? Why is it important to code securely? What value does software security add to a company’s overall well-being?

I was able to perform a thorough static analysis of the codebase and make determinations on whether flagged dependencies were accurrate or false-positives. It is important to code securely so that the users who access the systems we program are protected and maintain their level of trust.

> Which part of the vulnerability assessment was challenging or helpful to you?

The vulnerability assessment was a useful tool for understanding how to review and report code errors and vulnerable dependencies. Through the research done to compile the report I was also able to learn a lot more about encryption algorithms and ciphers.

> How did you increase layers of security? In the future, what would you use to assess vulnerabilities and decide which mitigation techniques to use?

In this project we increased the layers of security through the use of certificates and ensuring communication was performed over HTTPS. Going forward, I will be making sure I statically review my code frequently to ensure that it is error-free, that all my exceptions are handled correctly, and that I frequently check my dependencies with OWASP to ensure there are no current vulnerabilities and keeping my dependencies up-to-date.

> How did you make certain the code and software application were functional and secure? After refactoring the code, how did you check to see whether you introduced new vulnerabilities?

Through repeated testing and static code review I was able to ensure my code was functional and secure. I also reviewed the dependency check report and compared it to my code to ensure the code was not vulnerable in any way. After refactoring, I would run the dependency check again to ensure I did not introduce any new vulnerabilities.

> What resources, tools, or coding practices did you use that might be helpful in future assignments or tasks?

The most important tool I used throughout these assignments was the OWASP Maven dependency check report. I had never heard of nor used the tool before, but definitely will be using it going forward with any Java applications I develop to ensure they are secure.

> Employers sometimes ask for examples of work that you have successfully completed to show your skills, knowledge, and experience. What might you show future employers from this assignment?

I would show them the code compiled for this project, as it demonstrates my understanding of the Spring framework as well as a general sense of my abilities to network communcations between clients and servers securely.
