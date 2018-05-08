package com.prince.design;

/**
 *
 * https://github.com/ggerganov/wave-share
 *
 * <pre>
 * Serverless, peer-to-peer, local file sharing through sound
 *
 * The WebRTC technology allows two browsers running on different devices to connect with each other and exchange data.
 * There is no need to install plugins or download applications. To initiate the connection, the peers exchange contact
 * information (ip address, network ports, session id, etc.). This process is called "signaling". The WebRTC
 * specification does not define any standard for signaling - the contact exchange can be achieved by any protocol or
 * technology.
 *
 * In this project the signaling is performed via sound. The signaling sequence looks like this:
 * 1. Peer A broadcasts an offer for a WebRTC connection by encoding the session data into audio tones
 * 2. Nearby peer(s) capture the sound emitted by peer A and decode the WebRTC session data
 * 3. Peer B, who wants to establish connection with peer A, responds with an audio answer. The answer has peer B's
 *    contact information encoded in it. Additionally, peer B starts trying to connect to peer A
 * 4. Peer A receives the answer from peer B, decodes the transmitted contact data and allows peer B to connect
 * 5. Connection is established
 *
 * See image: file_sharing_via_sound.png
 *
 * An obvious limitation (feature) of the current approach is that only nearby devices (e.g. within the same room) can
 * establish connection with each other. Moreover, the devices have to be connected in the same local network, because
 * NAT is not available.
 *
 * Sound Tx/Rx:
 * The data communicated through sound contains the contact information required to initialize the WebRTC connection.
 * This data is stored in the Session Description Protocol (SDP) format. Since data-over-sound has significant limitations
 * in terms of bandwidth and robustness it is desirable to transmit as few data as possible.
 *
 * The sound packet containing the minimum required SDP data has the following format:
 * Size, [Byte]	Description
 *  1	Type of the SDP - Offer or Answer
 *  1	Packet size in bytes (not including ECC bytes)
 *  4	IP address of the transmitting peer
 *  2	Network port that will be used for the communication
 *  32	SHA-256 fingerprint of the session data
 *  40	ICE Credentials - 16 bytes username + 24 bytes password
 *  32	ECC correction bytes used to correct errors during Tx
 *
 * The total size of the audio packet is 112 bytes.
 *
 * Data-to-sound encoding:
 * The current approach uses a multi-frequency Frequency-Shift Keying (FSK) modulation scheme. The data to be transmitted
 * is first split into 4-bit chunks. At each moment of time, 3 bytes are transmitted using 6 tones - one tone for each
 * 4-bit chunk.
 * </pre>
 *
 * @author Prince Raj
 */
public class ServerlessFileSharing {

}
