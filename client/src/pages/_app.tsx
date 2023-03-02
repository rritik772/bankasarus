import HeaderComponent from '@/components/headerCompo/HeaderCompo'
import NavbarComponent from '@/components/navbarCompo/NavbarCompo'
import '@/styles/globals.css'
import { AppShell, MantineProvider } from '@mantine/core'
import type { AppProps } from 'next/app'

export default function App({ Component, pageProps }: AppProps) {
  return (
    <MantineProvider
      theme={{
        colorScheme: 'light'
      }}
    >
      <AppShell
        padding='md'
        header=<HeaderComponent />
        navbar=<NavbarComponent />
      >
        <Component {...pageProps} />
      </AppShell>
    </MantineProvider>

  )
}
