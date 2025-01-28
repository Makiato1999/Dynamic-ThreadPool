#!/bin/bash

# Docker Hub 用户名
DOCKER_HUB_USER="makiato1999"
REPO_NAME="dynamic-thread-pool"

# 定义镜像和标签列表
IMAGES=("dev-ops-test" "dev-ops-admin" "redis:7.2.0" "spryker/redis-commander:0.8.0")
TAGS=("dev-ops-test" "dev-ops-admin" "redis" "redis-commander")

# 登录 Docker Hub
echo "Logging in to Docker Hub..."
docker login || { echo "Docker login failed! Exiting."; exit 1; }

# 遍历镜像和标签
for i in "${!IMAGES[@]}"; do
    IMAGE="${IMAGES[$i]}"
    TAG="${TAGS[$i]}"
    TARGET_TAG="$DOCKER_HUB_USER/$REPO_NAME:$TAG"

    echo "Tagging $IMAGE as $TARGET_TAG..."
    docker tag "$IMAGE" "$TARGET_TAG" || { echo "Failed to tag $IMAGE! Skipping."; continue; }

    echo "Pushing $TARGET_TAG to Docker Hub..."
    docker push "$TARGET_TAG" || { echo "Failed to push $TARGET_TAG! Skipping."; continue; }
done

# 可选：清理本地的旧镜像标签
echo "Do you want to remove old local tags? (y/n)"
read -r REMOVE_TAGS
if [ "$REMOVE_TAGS" == "y" ]; then
    for IMAGE in "${IMAGES[@]}"; do
        echo "Removing local image: $IMAGE..."
        docker rmi "$IMAGE" || echo "Failed to remove $IMAGE. Skipping."
    done
fi

echo "All done!"
