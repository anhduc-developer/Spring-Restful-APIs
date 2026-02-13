import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './style/global.css'
import AppLayout from './layout.tsx'
createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <AppLayout/>
  </StrictMode>,
)
