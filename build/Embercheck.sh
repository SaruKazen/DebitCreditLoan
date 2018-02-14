curl --insecure -o- https://raw.githubusercontent.com/creationix/nvm/v0.31.0/install.sh | bash \
&& . ~/.bashrc \
&& cd ~/.nvm \
&& nvm install 5.6.0 \
&& nvm use 5.6.0 \
&& npm install ember-cli@2.18.2