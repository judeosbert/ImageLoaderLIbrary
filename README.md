# ImageLoaderLibrary
I have used RxJava,Retrofit for the purspose of fetching images. 
I have also used Koin for Dependency Injection. So I have placed an init method inside the sample application class.
We have 3 different sources of bitmaps. Memory,Diskcache and then network. 
When a view requests for an Image, it first checks in memory, then in DiskCache and network. the miss rate was very less when checked.
I have used and check the cases with unsplash photos api with 10 photos. 

On the fly request cancellations are still not done. I couldnt get proper access to lifecycle events of the app.
All other features are done and checked. 
The library is completely asynchronus and loads images in the same way. 

Please reach out to me incase something dosent go right.

