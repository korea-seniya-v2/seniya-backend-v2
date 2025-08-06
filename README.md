☕ Seniya V2 - Backend
스터디 그룹 매칭 및 관리 플랫폼 'Seniya'의 V2 백엔드 서버입니다.

<br>

<p align="center">
<a href="https://github.com/korea-seniya-v2/seniya-backend-v2/blob/main/LICENSE">
<img src="https://img.shields.io/badge/license-MIT-green?style=for-the-badge" alt="license" />
</a>
<img src="https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&logo=typescript&logoColor=white" alt="typescript"/>
<img src="https://img.shields.io/badge/NestJS-E0234E?style=for-the-badge&logo=nestjs&logoColor=white" alt="nestjs" />
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="mysql" />
</p>

<br>

✨ 주요 기능 (Features)
Seniya V2 서버는 스터디 관리를 위한 다음과 같은 핵심 기능들을 API로 제공합니다.

🧑‍🤝‍🧑 사용자 관리: JWT 기반의 안전한 회원가입, 로그인, 소셜 로그인 및 프로필 관리

📖 스터디 그룹: 스터디 그룹 생성, 조회, 검색, 참여 및 관리

💬 댓글 및 리뷰: 스터디에 대한 Q&A 및 후기 작성

💳 결제 시스템: 스터디 참여비 등 결제 및 정산 기능 연동

📁 파일 업로드: AWS S3를 활용한 이미지 등 관련 자료 업로드

<br>

🛠️ 기술 스택 (Tech Stack)
구분	스택
Framework	NestJS
Language	TypeScript
Database	MySQL
ORM	TypeORM
Authentication	Passport.js (JWT Strategy)
API Docs	Swagger
Deployment	AWS (EC2, S3 등)
Config	ConfigModule
Validation	class-validator, class-transformer

Sheets로 내보내기
<br>

⚙️ 시작하기 (Getting Started)
1. 환경 변수 설정 (Environment Variables)
프로젝트를 실행하기 전, 루트 디렉터리에 .env 파일을 생성하고 아래 내용을 프로젝트 환경에 맞게 수정하세요.

코드 스니펫

# Server
PORT=3000

# Database
DB_HOST=localhost
DB_PORT=3306
DB_USERNAME=root
DB_PASSWORD=your_password
DB_DATABASE=seniya_v2

# JWT
JWT_SECRET=your_jwt_secret_key
JWT_EXPIRATION_TIME=3600s

# AWS
AWS_ACCESS_KEY_ID=your_aws_access_key
AWS_SECRET_ACCESS_KEY=your_aws_secret_key
AWS_REGION=ap-northeast-2
AWS_S3_BUCKET_NAME=your_s3_bucket_name

# Social Login (Optional)
KAKAO_CLIENT_ID=...
NAVER_CLIENT_ID=...
GOOGLE_CLIENT_ID=...
2. 의존성 설치 (Dependencies Installation)
Bash

# npm 사용 시
$ npm install

# yarn 사용 시
$ yarn install
3. 애플리케이션 실행 (Running the App)
Bash

# 개발 모드 (Development)
$ npm run start:dev

# 서버가 http://localhost:3000 에서 실행됩니다.
<br>

📜 API 문서 (API Documentation)
서버를 실행한 후, 아래 주소로 접속하면 Swagger를 통해 API 문서를 확인하고 직접 테스트할 수 있습니다.

http://localhost:3000/api

<br>

👨‍💻 만든 사람들 (Authors)
Organization: korea-seniya-v2

<br>

📄 라이선스 (License)
이 프로젝트는 MIT 라이선스를 따릅니다.
