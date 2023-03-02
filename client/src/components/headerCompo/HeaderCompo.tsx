import { ActionIcon, Burger, Flex, Header, MediaQuery, Text } from "@mantine/core";
import { IconMoon } from "@tabler/icons-react";
import { FC } from "react";

type HeaderComponentProps = {
  isHidden: boolean
  setIsHidden: (e: boolean) => void
}

const HeaderComponent: FC<HeaderComponentProps> = ({ isHidden, setIsHidden }) => {
  return (
    <Header
      height={70}
      p='md'
    >

      <Flex
        justify='space-between'
      >
        <Flex
          align='center'
        >
          <MediaQuery largerThan="sm" styles={{ display: 'none' }}>
            <Burger
              opened={!isHidden}
              onClick={() => setIsHidden(!isHidden)}
              size="sm"
              mr="md"
            />
          </MediaQuery>

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
        </Flex>

        <ActionIcon>
          <IconMoon size={20} />
        </ActionIcon>
      </Flex>

    </Header>
  )
}

export default HeaderComponent;
