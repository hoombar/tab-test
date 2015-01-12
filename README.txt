A few things to highlight.

Time:
- I spent around 6 hours on the test, split over two sessions and wrapped around home life
(breaking for dinner, etc). I made a conscious effort not to spend too much longer than this.
Your feedback on the test is appreciated!

API:
- There's no API, I'm aware how volatile scraping data from a web page is. I would prefer for
there to be a reliable API. I haven't spent too much time error handling edge cases that could
happen with the DOM tree being changed on the web page, but this is typically something I would
do, given more time.

Employee Data:
- With just one employee, the DefaultEmployee data provider is used to provide the single employee
that should be shown if no network access and no stored data
- If there's no network connection and there's local data, the EmployeeDataLocal data provider is
 used to pull employee data from disk
- if there's an internet connection, new employees are grabbed from the site

Image loading:
- I'm using Picasso. I'm aware of things that could be used for loading/storing image data like
using LruCache, loading from memory, loading from disk, etc, but I didn't want to reinvent the
wheel.

Data persistence:
- I thought using SharedPreferences and serializing the employee list was quite a neat trick. One
 thing to be aware of with this method is that any changes to the employee object would break the
  deserialization of a previous version that has been stored. For example, if we refactored name
  to fullName, it would not deserialize the name.
- I wouldn't necessarily use this in production (I would probably use a database and an ORM), but
 I thought it might be a good demonstration of how I can think outside the box and abstract data
 persistence (decide on data storage at runtime by swapping it out for another
 DataPersister<Employee>)

 