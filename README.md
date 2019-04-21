#File Manager REST API


REST Service realise with SpringBoot.


API request : 

GET /file/
List all the files in the current path 

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
List all the files in the current directory by ascending file name.

GET /file/{id}
Get file information by id

GET /file/isdir
List all the directories

GET /path
return a string with the current directory
```
"file:///"
```

GET /download/{id}
Download a file by id

POST /upload
Upload a file in the current path.
A file with the key "file" is needed.

POST /move
Change the current path.
A string with the new path is needed. The path need to be finish with '/'. 
