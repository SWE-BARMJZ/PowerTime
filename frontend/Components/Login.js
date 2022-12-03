import { Button} from 'react-native';

function Login({navigation}) {
    return (
        <Button
          title="Log in"
          onPress={() =>
            navigation.navigate('Home')
          }
        />
      );
}

export default Login;
