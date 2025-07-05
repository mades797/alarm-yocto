FROM crops/poky:ubuntu-22.04
USER root
RUN pip install kas==4.7
RUN useradd maxime -u 1000
USER maxime

WORKDIR /work
