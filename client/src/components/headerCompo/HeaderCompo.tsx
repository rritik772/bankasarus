import { ActionIcon, Flex, Header, Text } from "@mantine/core";
import { IconMoon } from "@tabler/icons-react";

export default function HeaderComponent() {
  return (
    <Header
      height={70}
      p='md'
    >

      <Flex
        justify='space-between'
      >
        <Text
          href='/'
          component='a'
          c='blue'
          style={{
            letterSpacing: '0.7px',
            fontWeight: '500',
            fontSize: 'x-large',
            fontFamily: 'Roboto Slab'
          }}
        >
          Bankasarus
        </Text>

        <ActionIcon>
          <IconMoon size={20} />
        </ActionIcon>
      </Flex>

    </Header>
  )
}
