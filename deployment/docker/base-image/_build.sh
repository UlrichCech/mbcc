#!/usr/bin/env bash

echo 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 | docker login ghcr.io -u ulrichcech --password-stdin
if [ $? -eq 0 ]
then
  docker build --progress=plain --no-cache -t "mbcc-base:rockylinux-java17" .
  if [ $? -eq 0 ]
  then
    docker tag mbcc-base:rockylinux-java17 ghcr.io/ulrichcech/mbcc-base:rockylinux-java17
    docker push ghcr.io/ulrichcech/mbcc-base:rockylinux-java17
  fi
fi
