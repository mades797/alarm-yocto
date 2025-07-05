#!/bin/bash
docker run -it --entrypoint /bin/bash \
  -v .:/work \
  -v /home/maxime/sstate-cache/:/home/maxime/sstate-cache \
  -v /home/maxime/yocto-downloads/:/home/maxime/yocto-downloads \
  alarm:latest
