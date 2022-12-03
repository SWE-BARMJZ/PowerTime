import {
    Text,
    View,
    StyleSheet,
    Image,
    TextInput,
    Pressable
} from 'react-native';
import React from 'react';


export const SignUp = ({ navigation}) => {
    return (
        <View style={Styles.container}>
            <Image 
                source={logoPath}
                style={Styles.logo}
            /> 
            <View style={Styles.titleContainer}>
                <Text 
                    style={Styles.title}
                    adjustsFontSizeToFit
                >
                    Sign Up
                </Text>
            </View>
            <MakeTextInput type={"Email"}/>
            <MakeTextInput type={"First Name"}/>
            <MakeTextInput type={"Last Name"}/>
            <MakeTextInput type={"Password"}/>
            <Pressable
                style={Styles.button}
                onPress={() => submitCredentials(navigation)}
            >
                <Text style={Styles.buttonText}>Sign Up</Text>
            </Pressable>
            <View style={Styles.lastLine}>
                <Text style={{fontSize: 16}}>
                    Already have an account? 
                    <View style={Styles.inLineLink}>
                    <Pressable
                        onPress={() => goToLogin(navigation)}
                        >
                        <Text style={Styles.mediumLink}>
                            Log in
                        </Text>
                    </Pressable>
                    </View>
                </Text>
            </View>
        </View>
    );
};

const Styles = StyleSheet.create({
    container: {
        marginHorizontal: 20,
        flex: 1,
        justifyContent: 'flex-start',
        backgroundColor: "#dffaef"
    },

    logo: {
        width: '50%',
        height: '6%',
        resizeMode: 'cover'
    },

    title: {
        fontSize: 36,
        fontWeight: 'bold',
    },

    titleContainer: {
        width: '100%',
        height: '18%',
        justifyContent: 'center',
        paddingHorizontal: '10%',
    },

    textBoxContainer: {
        backgroundColor: 'white',
        height: '8%',
        marginVertical: 15,
        borderRadius: 10,
        padding: 5,
    },

    textBox: {
        paddingHorizontal: 15,
        fontSize: 16,
    },

    button: {
        marginVertical: 20,
        backgroundColor: '#0066F9',
        borderRadius: 20,
        alignItems: 'center',
        justifyContent: 'center',
        height : '8%'
    },

    buttonText: {
        color: 'white',
        fontSize: 20,
        fontWeight: 'bold',
    },

    farLink: {
        marginVertical: 20,
        marginLeft: 20,
        width: '50%'
    },

    lastLine: {
        marginVertical: 20,
        justifyContent: 'center',
        alignItems: 'center',
    },

    smallLink: {
        fontWeight: 'bold',
        textDecorationLine: 'underline',
        fontSize: 14
    },

    inLineLink: {
    },

    mediumLink: {
        fontWeight: 'bold',
        textDecorationLine: 'underline',
        fontSize: 16
    }

});

const logoPath = require('../assets/images/LOGO.png'); 

function MakeTextInput(props) {
    const [value, onChangeText] = React.useState('');

    return (
        <View style={Styles.textBoxContainer}>
            <Text style={{fontSize:16,margin:3}}>{props.type}</Text>
            <TextInput
                style={Styles.textBox}
                placeholder={'Enter your ' + props.type}
                placeholderTextColor={'gray'}
                secureTextEntry={props.type==='Password'}
                onChangeText={text => {
                    onChangeText(text)
                    console.log(props.type + ": " + text)
                    }
                }
                value={value}
            />
        </View>     
    );
  }

function submitCredentials(navigation){
    //TODO
    //SEND CRED TO BACKEND
    goToLogin(navigation)
}

function goToLogin(navigation){
    navigation.navigate('Login')
}