#! /bin/bash

docker build . -t clientserver
docker run -p 2000:2000 clientserver