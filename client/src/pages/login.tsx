import { Button, Center, Container, Flex, Loader, Paper, PasswordInput, Text, TextInput } from "@mantine/core"
import { useForm } from "@mantine/form"
import { IconAt, IconLock, IconLogin } from "@tabler/icons-react"

export default function Login() {
  const form = useForm({
    initialValues: {
      email: '',
      password: '',
    },
    validate: {
      email: v => (/^\S+@\S+$/.test(v) ? null : 'Invalid email'),
      password: v => (v.length >= 8 ? null : 'Password too short'),
    }
  })

  return (
    <Flex
      p={8}
      justify='center'
      align='center'
    >
      <Paper
        withBorder
        p='lg'
        shadow='sm'
        style={{ width: '100%' }}
        miw={250}
        maw={500}
      >
        <Text
          style={{
            fontFamily: 'Roboto Slab',
            fontSize: 'x-large'
          }}
          mb={{ base: 'xl', sm: 'md' }}
        >
          Login
        </Text>


        <form
          onSubmit={() => { }}
        >

          <TextInput
            type='email'
            label="Email"
            placeholder="Someone"
            icon=<IconAt />
            mb='lg'
            {...form.getInputProps('email')}
          />
          <PasswordInput
            label="Password"
            placeholder="***"
            icon=<IconLock />
            mb='lg'
            {...form.getInputProps('password')}
          />

          <Button
            style={{ float: 'right' }}
            leftIcon={<IconLogin size={20} />}
          >Login</Button>

        </form>
      </Paper>
    </Flex>
  )
}
