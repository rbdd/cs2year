from PIL import Image
import os

def extract_message_from_image(image_filename):
    # Get the path of the script and assume the image is in the same directory
    script_path = os.path.dirname(os.path.abspath(__file__))
    image_path = os.path.join(script_path, image_filename)

    # Read the image
    image = Image.open(image_path)
    pixels = image.load()

    # Initialize variables
    extracted_bits = []
    bit_count = 0

    # Extract the length
    for x in range(image.width):
        for y in range(image.height):
            pixel = pixels[x, y]
            for channel in range(3):  # RGB channels
                extracted_bits.append(pixel[channel] & 1)
                bit_count += 1
                if bit_count == 32:
                    # Calculate text length
                    text_length = int("".join(map(str, extracted_bits)), 2)
                    extracted_message = extract_text(pixels, text_length, image.width, image.height)
                    return extracted_message

def extract_text(pixels, text_length, image_width, image_height):
    # Extract the message bits
    extracted_bytes = []
    byte_count = 0
    for x in range(1, text_length + 1):
        pixel = pixels[(x-1) % image_width, (x-1) // image_width]
        for channel in range(3):  # RGB channels
            extracted_bytes.append(pixel[channel] & 1)
            byte_count += 1
            if byte_count == text_length * 8:
                # Assemble the message
                byte_array = bytearray(extracted_bytes)
                message = byte_array.decode('utf-8')
                return message

# Usage
image_filename = 'Steganography-774202169.png'  # Replace with your image filename
extracted_message = extract_message_from_image(image_filename)
print(extracted_message)
