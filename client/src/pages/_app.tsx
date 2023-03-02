import { useState } from 'react';
import { AppShell, MantineProvider } from '@mantine/core'
import type { AppProps } from 'next/app'

import HeaderComponent from '@/components/headerCompo/HeaderCompo'
import NavbarComponent from '@/components/navbarCompo/NavbarCompo'

import '@/styles/globals.css'

export default function App({ Component, pageProps }: AppProps) {
  const [isHidden, setisHidden] = useState(true);

  return (
    <MantineProvider
      theme={{
        colorScheme: 'light'
      }}
    >
      <AppShell
        padding='md'
        header={<HeaderComponent isHidden={isHidden} setIsHidden={(e: boolean) => setisHidden(e)} />}
        navbar={<NavbarComponent isHidden={isHidden} />}
        navbarOffsetBreakpoint='sm'
      >
        <Component {...pageProps} />
      </AppShell>
    </MantineProvider>

  )
}
