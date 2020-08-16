FROM ubuntu:focal-20200720

COPY ./product /usr/src/app

WORKDIR /usr/src/app

RUN chmod a+x /usr/src/app/server

ENTRYPOINT ["/usr/src/app/server"]

EXPOSE 2000