# QA Notes

## Notes on [Concurrency Lesson 1](concurrency1.md)

- Timings breakdown like this: https://git.generalassemb.ly/GA-Cognizant/course-documentation/blob/master/lesson-best-practices.md#example-lesson-timing
- What does “Sizing: 5 (biggest)“ mean in Activities List?
- Probably worth explaining why you need to “To propagate the interrupt” in the catch block. Also, since this is a pattern/idiom as stated, if the student does not completely understand it now that is OK for now.
    - (Victor) - Done
- To explain locking, I would re-order the MUTEX object to be first (easier to visualize) and the synchronized methods (with intrinsic locks) to be second.
  - Alex - I am not sure which example you are referring to. Can you please make the change.
  - (Alex) You can ignore. It makes sense the way it is now.
- Something missing here “The two events - ordering the book and debiting the credit card, must occur atomically - you don't ever want a situation “
    - (Victor) Corrected. Plase review.
- In Wait-Notify ReaderWriterExample, private Object mutex = new Object(); —> should be final and all caps?
    - (Victor) Corrected.
- 

## Notes on [Concurrency Lesson 2](concurrency2.md)

- Maybe mention oswego and Doug Lea https://en.wikipedia.org/wiki/Doug_Lea is where the java.util.concurrent package came from (and Java continues to evolve thanks to contributions from people in the community)
    - (Victor) Done, please review
- Students may not know what this means "pinned the CPU"
    - (Victor) Done, please review
- "or a browser DOM" --> how does a Web browser DOM fit in this context of Java?
- Maybe another (simpler?) word to consider using instead of "inter-collation" is "ordering"
- "each implementing some valuable concurrency design pattern" --> reference a book or other resource?
    - (Victor) Done
- In the ReadWriteLock example, it may be better to break into two executable programs. Also, if using ConcurrentHashMap, you'd need to explain it and how it's used in this context to hold different possible portfolio values.
    - (Victor) there are four lines inserted. I have now made it very clear which lines to uncomment, and showed the IntelliJ keyboard shortcut for that. Do you think that is fine?
    - (Alex) looks good to me



## Other
- My Edits are in [this branch - concurrency_qa_alex](https://git.generalassemb.ly/GA-Cognizant/foundational-java/blob/concurrency_qa_alex)

