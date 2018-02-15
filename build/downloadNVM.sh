#/bin/bash
curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.33.8/install.sh | bash
source  ~/.bashrc
cd ~/.nvm ;
nvm install v9.5.0 ;
nvm use 9.5.0 ;
npm install -g ember-cli@3.0.0;