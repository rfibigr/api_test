#File Manager REST API


REST Service realise with SpringBoot.


API request : 

GET /file/
List all the file with the following format 

```json
 {
        "id": 0,
        "fileName": "lost+found",
        "size": 16384,
        "path": "file:///lost+found/",
        "directory": true
    },
    {
        "id": 1,
        "fileName": "bin",
        "size": 12288,
        "path": "file:///bin/",
        "directory": true
    },
```

GET /file/sorted
List of file sorted by ascii order

GET /file/{id}
Detail file detail for a specific Id

GET /file/isdir
List all the directory

GET /path
return a string with the current path

```
"file:///"
```

GET /download/{id}
Download a file by id

POST /upload
a parameter file with the key "file" is needed.
Upload a file in the current path.

POST /move
A string with the new path is needed. The path need to be finish with a '/'. 
Change the current path 

example body : /home/username/Documents/
