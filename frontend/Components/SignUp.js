import {
    Text,
    View,
    StyleSheet,
    Image,
    TextInput,
    Pressable,
    Platform
} from 'react-native';
import React from 'react';

export const SignUp = ({ navigation}) => {
    return (
        <View style={Styles.device}>
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
            {Platform.OS == 'web' &&
            <View  style={Styles.imgContainer}>
            <Image
                source={require('../assets/images/themeImage.png')}
                style={Styles.themeImage}
            />
            </View>}
        </View>
    );
};

const Styles = StyleSheet.create({
    device: {
        flexDirection: 'row',
        backgroundColor: '#dffaef',
        alignItems: 'flex-start'
    },

    container: {
        marginHorizontal: '5%',
        flex: 2,
        justifyContent: 'flex-start',
        backgroundColor: "#dffaef",
        height: '100%'
    },

    logo: {
        height: '15%',
        width: '54%',
        resizeMode: 'contain'
    },

    imgContainer: {
        marginTop: "5%",
        marginHorizontal: '5%',
        flex: 4,
        justifyContent: 'center',
        backgroundColor: "#dffaef",
        height: '100%'
    },

    themeImage: {
        resizeMode: 'contain',
        height: '100%',
        borderRadius: 38,
    },

    title: {
        fontSize: 36,
        fontWeight: 'bold',
    },

    titleContainer: {
        width: '100%',
        height: '17%',
        justifyContent: 'center',
        paddingLeft: Platform.OS !== 'web' ? '10%' : '40%',
    },

    textBoxContainer: {
        backgroundColor: 'white',
        height: Platform.OS === 'web' ? '12%' : '8%',
        marginVertical: '4%',
        borderRadius: 10,
        padding: '2%',
    },

    textBox: {
        paddingHorizontal: '3%',
        fontSize: 16,
    },

    button: {
        marginVertical: '4%',
        backgroundColor: '#0066F9',
        borderRadius: 20,
        alignItems: 'center',
        justifyContent: 'center',
        height: Platform.OS === 'web' ? '12%' : '8%',
    },

    buttonText: {
        color: 'white',
        fontSize: 20,
        fontWeight: 'bold',
    },

    farLink: {
        marginVertical: '4%',
        marginLeft: '5%',
        width: '50%'
    },

    lastLine: {
        marginVertical: '4%',
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