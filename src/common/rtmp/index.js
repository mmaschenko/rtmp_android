// index.js

import React, { Component, PropTypes } from 'react'
import { NativeModules, Dimensions, View, Text, Image, StyleSheet, TouchableOpacity } from 'react-native'

import RTMPStreamingView from './RTMPStreamingView'

const NativeRTMPModule = NativeModules.RTMPModule;

const { width, height } = Dimensions.get('window');

export default class SampleApp extends Component {
  constructor(props) {
    super(props);
    this.publishing = false;
    this.state = {
      publishButtonState: 'Start'
    }
  }

  async startPublish(rtmpUrl) {
    const success = await NativeRTMPModule.startStream(rtmpUrl);
    return success;
  }

  async stopPublish() {
    const success = await NativeRTMPModule.stopStream();
    return success;
  }

  async switchCamera() {
    const success = await NativeRTMPModule.switchCamera();
    return success;
  }

  async switchCamera() {
    const success = await NativeRTMPModule.switchCamera();
    return success;
  }

  async onSettingButtonPressed() {
    const success = await NativeRTMPModule.changeSettings({
      // width: 300,
      // height: 640,
      // fps: 30,
      bitRate: 600 * 1024,
      // hardwareRotation: false,
      // rotation: 90
    });
    console.log('mikle', NativeRTMPModule)
    return success;
    
  }

  onSwitchButtonPressed() {
    //
    this.switchCamera()
  }

  onPublishButtonPressed() {
    this.publishing = !this.publishing;
    this.setPublishButtonState();

    if (this.publishing) {
      const rtmpURL = this.props.rtmpURL;
      this.startPublish(rtmpURL);
    } else {
      this.stopPublish();
    }
  }

  setPublishButtonState() {
    if (this.publishing) {
      this.setState({ publishButtonState: 'Stop' })
    } else {
      this.setState({ publishButtonState: 'Start' })
    }
  }

  renderTopButtons() {
    return (
      <View style={styles.topButtons}>
        {this.renderSettingButton()}
        {this.renderSwitchButton()}
      </View>
    )
  }

  renderBottomButtons() {
    return (
      <View style={styles.bottomButtons}>
        {this.renderPublishButton()}
      </View>
    )
  }

  renderSettingButton() {
    return (
      <TouchableOpacity style={{ paddingHorizontal: 15 }} onPress={() => this.onSettingButtonPressed()}>
        <Image style={{ flex: 1, justifyContent: 'center', width: 50, height: 50 }}
          source={{ uri: 'https://cdn1.iconfinder.com/data/icons/video-actions-files-1/24/camera_record_cam_movie_film_settings-512.png' }}
          resizeMode={Image.resizeMode.contain} />
      </TouchableOpacity>
    )
  }

  renderSwitchButton() {
    return (
      <TouchableOpacity style={{ paddingHorizontal: 15 }} onPress={() => this.onSwitchButtonPressed()}>
        <Image style={{ flex: 1, justifyContent: 'center', width: 50, height: 50 }}
          source={{ uri: 'http://www.iconsplace.com/download/orange-switch-camera-512.png' }}
          resizeMode={Image.resizeMode.contain} />
      </TouchableOpacity>
    )
  }

  renderPublishButton() {
    return (
      <View style={styles.publishButtonContainer}>
        <TouchableOpacity onPress={() => this.onPublishButtonPressed()}>
          <Image style={[styles.publishButton, { width: 50, height: 50}]}
            source={{ uri: 'https://facebook.github.io/react-native/docs/assets/favicon.png' }}
            resizeMode={'contain'}/>
        </TouchableOpacity>
      </View >
    )
  }

  renderCamera() {
    return (
      //<View style={styles.container}>
      <RTMPStreamingView style={StyleSheet.absoluteFill} />
      //</View>
    )
  }

  renderGap() {
    return (
      <View style={{ flex: 10, flexDirection: 'column' }} />
    );
  }

  render() {
    return (
      <View style={{ flex: 1, backgroundColor: 'transparent', width, borderColor: 'red', borderWidth: 1 }}>
        {this.renderCamera()}
        {this.renderTopButtons()}
        {this.renderGap()}
        {this.renderBottomButtons()}
      </View>
    );
  }
}



const styles = StyleSheet.create({
  topButtons: {
    flex: 1,
    flexDirection: 'row',
    justifyContent: 'space-between',
    paddingTop: 8,
    paddingBottom: 0,
    borderColor: 'red',
    borderWidth: 1 

  },
  bottomButtons: {
    flex: 2,
    flexDirection: 'row',
    justifyContent: 'space-between',
    padding: 14,
    borderColor: 'red', borderWidth: 1 
  },
  publishButtonContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  publishButtonText: {
    justifyContent: 'center',
    color: 'black',
    backgroundColor: 'transparent'
  },
  publishButton: {
    flex: 1,
    alignSelf: 'center',
    alignItems: 'center',
    justifyContent: 'center'
  },
  container: {
    width: width,
    height: height,
    position: 'absolute',
    top: 0,
    left: 0,
    flex: 10,
    flexDirection: 'column'
  },
  //container: StyleSheet.absoluteFillObject
})
