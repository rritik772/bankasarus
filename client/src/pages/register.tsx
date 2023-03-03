import { Button, Flex, Group, Paper, PasswordInput, SimpleGrid, Text, TextInput } from '@mantine/core';
import { useForm } from '@mantine/form';
import { IconAbc, IconAt, IconLock, IconLogin } from '@tabler/icons-react';

export default function Register() {
  const form = useForm({
    initialValues: {
      first_name: '',
      last_name: '',
      email: '',
      password: '',
      repassword: '',
      birth_date: '',
    },
    validate: {
      first_name: v => (v.length >= 4 ? null : 'First name too short'),
      last_name: v => (v.length >= 4 ? null : 'Last name too short'),
      email: v => (/^\S+@\S+$/.test(v) ? null : 'Invalid email'),
      password: v => (v.length >= 8 ? null : 'Password too short'),
      repassword: (v, vs) => (v == vs.password ? null : 'Passwords do not match'),
      birth_date: v => (v.length !== 0 ? null : 'Enter date of birth')
    }
  })

  const handleSubmit = () => {
    if (!form.isValid()) {
      return;
    }

    const first_name = form.getInputProps('first_name').value;
    const last_name = form.getInputProps('last_name').value;
    const email = form.getInputProps('email').value;
    const password = form.getInputProps('password').value;
    const repassword = form.getInputProps('repassword').value;
    const birth_date = form.getInputProps('birth_date').value;

    console.log({ first_name, last_name, email, password, repassword, birth_date })

  }

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
          Register
        </Text>

        <form onSubmit={form.onSubmit(() => { })}>
          <SimpleGrid
            cols={2}
            spacing='md'
            mb='md'
            breakpoints={[
              { maxWidth: 720, cols: 1, spacing: 'sm' },
            ]}
          >

            <TextInput
              withAsterisk
              label='First Name'
              placeholder='John'
              icon=<IconAbc />
              {...form.getInputProps('first_name')}
            />
            <TextInput
              withAsterisk
              label='Last name'
              placeholder='Doe'
              icon=<IconAt />
              {...form.getInputProps('last_name')}
            />

          </SimpleGrid>

          <TextInput
            withAsterisk
            type='email'
            label='Email'
            placeholder='johndoe@gmail.com'
            icon=<IconAt />
            mb='md'
            {...form.getInputProps('email')}
          />

          <SimpleGrid
            cols={2}
            spacing='md'
            mb='md'
            breakpoints={[
              { maxWidth: 720, cols: 1, spacing: 'sm' },
            ]}
          >

            <PasswordInput
              withAsterisk
              label='Password'
              placeholder='****'
              icon=<IconLock />
              {...form.getInputProps('password')}
            />

            <PasswordInput
              withAsterisk
              label='Re-enter Password'
              placeholder='****'
              icon=<IconLock />
              {...form.getInputProps('repassword')}
            />
          </SimpleGrid>

          <TextInput
            type='date'
            mb='md'
            {...form.getInputProps('birth_date')}
          />

          <Button
            type='submit'
            onClick={handleSubmit}
            style={{ float: 'right' }}
            leftIcon={<IconLogin size={20} />}
          >Login</Button>

        </form>
      </Paper>
    </Flex>
  )
}
