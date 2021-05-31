#!/bin/bash
set -eo pipefail
STACK=blank-java
if [[ $# -eq 1 ]] ; then
    STACK=$1
    echo "Deleting stack $STACK"
fi
FUNCTION=$(aws cloudformation describe-stack-resource --stack-name $STACK --logical-resource-id function --query 'StackResourceDetail.PhysicalResourceId' --output text)
aws cloudformation delete-stack --stack-name $STACK
echo "Deleted $STACK stack."

if [ -f bucket-name.txt ]; then
    ARTIFACT_BUCKET=$(cat bucket-name.txt)
	echo "ARTIFACT_BUCKET $ARTIFACT_BUCKET exists."
    if [[ ! $ARTIFACT_BUCKET =~ lambda-artifacts-[a-z0-9]{16} ]] ; then
        echo "Bucket was not created by this application. Skipping."
    else
        aws s3 rb --force s3://$ARTIFACT_BUCKET; 
		rm bucket-name.txt;
    fi
fi

aws logs delete-log-group --log-group-name /aws/lambda/$FUNCTION; 
echo "Deleted LOG GROUP FOR /aws/lambda/$FUNCTION."

rm -f out.yml out.json
echo "Deleted $STACK stack."

rm -rf build .gradle target
echo "Deleted $STACK stack."
# remove all buckets starting with lambda : 
# $ for b in $(aws s3api list-buckets --query 'Buckets[?starts_with(Name, `lambda-artifacts-`) == `true`].Name' --output text) ; do  aws s3 rb --force s3://$b;  done;
