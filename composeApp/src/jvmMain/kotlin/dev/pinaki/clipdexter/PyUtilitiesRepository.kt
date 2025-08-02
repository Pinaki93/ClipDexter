package dev.pinaki.clipdexter

import dev.pinaki.clipdexter.services.PythonUtilitiesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

data class UtilityItem(
    val name: String,
    val pythonCodeId: String
)

class PyUtilitiesRepository(
    private val dataStore: PythonUtilitiesDataStore
) {
    
    val isSetup: Flow<Boolean> = dataStore.isSetup
    
    suspend fun setupInitialData() {
        if (!dataStore.isSetup.first()) {
            val initialUtilities = getInitialUtilities()
            initialUtilities.forEach { (id, code) ->
                dataStore.saveUtility(id, code)
            }
            dataStore.markAsSetup()
        }
    }
    
    suspend fun getUtilities(): List<UtilityItem> {
        val utilities = dataStore.getAllUtilities()
        return utilities.map { (id, _) ->
            UtilityItem(
                name = getUtilityName(id),
                pythonCodeId = id
            )
        }
    }
    
    suspend fun getPythonCode(id: String): String? {
        return dataStore.getUtility(id)
    }
    
    suspend fun saveUtility(id: String, name: String, pythonCode: String) {
        dataStore.saveUtility(id, pythonCode)
    }
    
    suspend fun deleteUtility(id: String) {
        dataStore.deleteUtility(id)
    }
    
    private fun getUtilityName(id: String): String {
        return when (id) {
            "uppercase" -> "Convert to Uppercase"
            "lowercase" -> "Convert to Lowercase"
            "capitalize" -> "Capitalize Each Word"
            "trim_whitespace" -> "Trim Extra Whitespace"
            "remove_line_breaks" -> "Remove All Line Breaks"
            "extract_first_url" -> "Extract First URL"
            "extract_all_urls" -> "Extract All URLs"
            "extract_emails" -> "Extract All Email Addresses"
            "extract_numbers" -> "Extract All Numbers"
            "csv_to_json" -> "Convert CSV to JSON"
            "pretty_print_json" -> "Pretty-print JSON"
            "minify_json" -> "Minify JSON"
            "encode_base64" -> "Encode to Base64"
            "decode_base64" -> "Decode from Base64"
            "count_words_chars" -> "Count Words and Characters"
            "remove_html_tags" -> "Remove All HTML Tags"
            "escape_html" -> "Escape for HTML"
            "tabs_to_spaces" -> "Convert Tabs to Spaces"
            "sort_lines" -> "Sort Lines Alphabetically"
            "deduplicate_lines" -> "Deduplicate Lines"
            else -> id
        }
    }
    
    private fun getInitialUtilities(): Map<String, String> {
        return mapOf(
            "uppercase" to """
import sys

def convert_to_uppercase(text):
    return text.upper()

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = convert_to_uppercase(input_text)
    print(result)
""".trimIndent(),
            
            "lowercase" to """
import sys

def convert_to_lowercase(text):
    return text.lower()

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = convert_to_lowercase(input_text)
    print(result)
""".trimIndent(),
            
            "capitalize" to """
import sys

def capitalize_each_word(text):
    return text.title()

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = capitalize_each_word(input_text)
    print(result)
""".trimIndent(),
            
            "trim_whitespace" to """
import sys
import re

def trim_extra_whitespace(text):
    # Remove leading/trailing whitespace and normalize internal whitespace
    return re.sub(r'\\s+', ' ', text.strip())

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = trim_extra_whitespace(input_text)
    print(result)
""".trimIndent(),
            
            "remove_line_breaks" to """
import sys

def remove_line_breaks(text):
    return text.replace('\\n', ' ').replace('\\r', ' ')

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = remove_line_breaks(input_text)
    print(result)
""".trimIndent(),
            
            "extract_first_url" to """
import sys
import re

def extract_first_url(text):
    url_pattern = r'https?://[^\\s<>"{}|\\\\^`\\[\\]]+'
    match = re.search(url_pattern, text)
    return match.group(0) if match else "No URL found"

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = extract_first_url(input_text)
    print(result)
""".trimIndent(),
            
            "extract_all_urls" to """
import sys
import re

def extract_first_url(text):
    url_pattern = r'https?://[^\\s<>"{}|\\\\^`\\[\\]]+'
    match = re.search(url_pattern, text)
    return match.group(0) if match else "No URL found"

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = extract_first_url(input_text)
    print(result)
""".trimIndent(),
            
            "extract_emails" to """
import sys
import re

def extract_emails(text):
    email_pattern = r'[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}'
    emails = re.findall(email_pattern, text)
    return '\\n'.join(emails) if emails else "No email addresses found"

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = extract_emails(input_text)
    print(result)
""".trimIndent(),
            
            "extract_numbers" to """
import sys
import re

def extract_numbers(text):
    # Find all numbers (integers and decimals)
    number_pattern = r'\\b\\d+(\\.\\d+)?\\b'
    numbers = re.findall(number_pattern, text)
    return '\\n'.join(numbers) if numbers else "No numbers found"

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = extract_numbers(input_text)
    print(result)
""".trimIndent(),
            
            "csv_to_json" to """
import sys
import csv
import json
from io import StringIO

def csv_to_json(csv_text):
    try:
        # Read CSV from string
        csv_file = StringIO(csv_text)
        reader = csv.DictReader(csv_file)
        
        # Convert to list of dictionaries
        data = list(reader)
        
        # Convert to JSON
        return json.dumps(data, indent=2)
    except Exception as e:
        return f"Error converting CSV to JSON: {str(e)}"

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = csv_to_json(input_text)
    print(result)
""".trimIndent(),
            
            "pretty_print_json" to """
import sys
import json

def pretty_print_json(json_text):
    try:
        # Parse JSON
        data = json.loads(json_text)
        
        # Pretty print with indentation
        return json.dumps(data, indent=2)
    except json.JSONDecodeError as e:
        return f"Invalid JSON: {str(e)}"
    except Exception as e:
        return f"Error: {str(e)}"

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = pretty_print_json(input_text)
    print(result)
""".trimIndent(),
            
            "minify_json" to """
import sys
import json

def minify_json(json_text):
    try:
        # Parse JSON
        data = json.loads(json_text)
        
        # Minify (remove all whitespace)
        return json.dumps(data, separators=(',', ':'))
    except json.JSONDecodeError as e:
        return f"Invalid JSON: {str(e)}"
    except Exception as e:
        return f"Error: {str(e)}"

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = minify_json(input_text)
    print(result)
""".trimIndent(),
            
            "encode_base64" to """
import sys
import base64

def encode_base64(text):
    try:
        # Encode text to bytes, then to base64
        text_bytes = text.encode('utf-8')
        encoded = base64.b64encode(text_bytes)
        return encoded.decode('utf-8')
    except Exception as e:
        return f"Error encoding to base64: {str(e)}"

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = encode_base64(input_text)
    print(result)
""".trimIndent(),
            
            "decode_base64" to """
import sys
import base64

def decode_base64(encoded_text):
    try:
        # Decode base64 to bytes, then to text
        decoded_bytes = base64.b64decode(encoded_text)
        return decoded_bytes.decode('utf-8')
    except Exception as e:
        return f"Error decoding from base64: {str(e)}"

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = decode_base64(input_text)
    print(result)
""".trimIndent(),
            
            "count_words_chars" to """
import sys

def count_words_and_chars(text):
    # Count characters (including spaces)
    char_count = len(text)
    
    # Count characters (excluding spaces)
    char_count_no_spaces = len(text.replace(' ', ''))
    
    # Count words
    word_count = len(text.split())
    
    # Count lines
    line_count = len(text.splitlines())
    
    result = f"Characters (including spaces): {char_count}\\n"
    result += f"Characters (excluding spaces): {char_count_no_spaces}\\n"
    result += f"Words: {word_count}\\n"
    result += f"Lines: {line_count}"
    
    return result

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = count_words_and_chars(input_text)
    print(result)
""".trimIndent(),
            
            "remove_html_tags" to """
import sys
import re

def remove_html_tags(text):
    # Remove HTML tags
    clean_text = re.sub(r'<[^>]+>', '', text)
    return clean_text

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = remove_html_tags(input_text)
    print(result)
""".trimIndent(),
            
            "escape_html" to """
import sys
import html

def escape_html(text):
    return html.escape(text)

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = escape_html(input_text)
    print(result)
""".trimIndent(),
            
            "tabs_to_spaces" to """
import sys

def tabs_to_spaces(text, tab_size=4):
    return text.expandtabs(tab_size)

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = tabs_to_spaces(input_text)
    print(result)
""".trimIndent(),
            
            "sort_lines" to """
import sys

def sort_lines_alphabetically(text):
    lines = text.splitlines()
    sorted_lines = sorted(lines)
    return '\\n'.join(sorted_lines)

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = sort_lines_alphabetically(input_text)
    print(result)
""".trimIndent(),
            
            "deduplicate_lines" to """
import sys

def deduplicate_lines(text):
    lines = text.splitlines()
    unique_lines = list(dict.fromkeys(lines))  # Preserves order
    return '\\n'.join(unique_lines)

if __name__ == "__main__":
    input_text = INPUT_PLACEHOLDER
    result = deduplicate_lines(input_text)
    print(result)
""".trimIndent()
        )
    }
} 