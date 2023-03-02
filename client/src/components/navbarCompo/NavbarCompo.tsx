import { Navbar, NavLink, ScrollArea, Text } from "@mantine/core";
import { IconAlien, IconHome, IconLogin, IconUserPlus } from "@tabler/icons-react";
import { FC, useEffect } from "react";

const NavbarComponent: FC<{ isHidden: boolean }> = ({ isHidden }) => {
  useEffect(() => console.log(isHidden), [isHidden])
  return (
    <Navbar
      width={{
        base: 300,
      }}
      hidden={isHidden}
      hiddenBreakpoint='sm'
    >
      <Navbar.Section>{}</Navbar.Section>

      <Navbar.Section p='md' grow component={ScrollArea}>
        <NavLink
          href='/'
          component='a'
          label='Home'
          icon=<IconHome size={21} />
        />

        <NavLink
          href='/register'
          component='a'
          label='Register'
          icon=<IconUserPlus size={21} />
        />

        <NavLink
          href='/login'
          component='a'
          label='Login'
          icon=<IconLogin size={21} />
        />

        <NavLink
          href='/aboutme'
          component='a'
          label='About Me'
          icon=<IconAlien size={21} />
        />
      </Navbar.Section>

      <Navbar.Section p='md'>
        <Text
          variant='gradient'
          gradient={{ from: 'orange.9', to: 'indigo.9' }}
          fz='xl'
          style={{
            fontFamily: 'Pacifico'
          }}
        >Made By Ritik Ranjan</Text>
      </Navbar.Section>

    </Navbar>
  )
}

export default NavbarComponent;
