# Freelancer Frontend (React + Vite)

![Freelancer App Screenshot](./public/readme-screenshot.svg)

Brief frontend for a Freelancer marketplace application. This React + Vite app contains the public UI used by clients, freelancers and admins to browse jobs, post jobs, send proposals, and manage tasks and payments.

**Key Features:**
- Browse and search available jobs
- User auth for clients and freelancers (login/register)
- Post jobs, send proposals, and manage applications
- Dashboards for Clients, Freelancers, and Admins
- Task and payment views for project management

**Tech Stack:**
- Frontend: React, Vite, JSX, CSS
- State/context: React Context API
- API: Fetch/axios to a Spring Boot backend (see parent project)

**Repository structure (frontend):**
- `public/` — static assets and public files (images, index.html)
- `src/` — React source code
	- `components/` — reusable UI components
	- `Pages/` — route pages (client, freelancer, admin)
	- `services/` — API service wrappers
	- `context/` — global React contexts (e.g. `UserContext`)

Getting started (frontend)
1. Ensure Node.js (16+) and npm are installed.
2. From this folder (`frelancer/Frontend/freelance`) install dependencies:

```powershell
cd "d:\wpj project3\frelancer\Frontend\freelance"
npm install
```

3. Run the dev server:

```powershell
npm run dev
```

4. Open the URL shown in the terminal (usually `http://localhost:5173`).

Notes about backend
- The full project includes a Spring Boot backend located in the parent Java project folder. The frontend expects a backend API providing authentication, jobs, proposals, tasks, and payments endpoints.
- Configure the backend base URL in `src/services/api.js` or environment variables if present.

Environment variables
- If the project uses `.env` files, create a `.env` in this folder and set variables like `VITE_API_BASE_URL` to point to the backend.

Contributing
- Create a feature branch: `git checkout -b feat/your-feature`
- Commit changes with a clear message: `git commit -am "feat: add X"`
- Open a PR to `main` when ready.

Adding this README and image to Git (example):

```powershell
git add README.md public/readme-screenshot.svg
git commit -m "docs: Add frontend README and README image"
git push origin main
```

If you want, I can also update the root README to reference this frontend README and link to the backend README.
