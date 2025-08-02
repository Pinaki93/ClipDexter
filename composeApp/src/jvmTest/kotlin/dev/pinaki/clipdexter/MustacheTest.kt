package dev.pinaki.clipdexter

import com.github.mustachejava.DefaultMustacheFactory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import java.io.StringReader
import java.io.StringWriter

class MustacheTest {

    @Test
    fun testSimpleVariableSubstitution() {
        val template = "Hello, {{name}}!"
        val data = mapOf("name" to "World")
        
        val result = compileAndExecute(template, data)
        assertEquals("Hello, World!", result)
    }

    @Test
    fun testConditionalRendering() {
        val template = """
            {{#show}}
            This is visible
            {{/show}}
            {{^show}}
            This is hidden
            {{/show}}
        """.trimIndent()
        
        val dataTrue = mapOf("show" to true)
        val dataFalse = mapOf("show" to false)
        
        val resultTrue = compileAndExecute(template, dataTrue)
        val resultFalse = compileAndExecute(template, dataFalse)
        
        assertTrue(resultTrue.contains("This is visible"))
        assertTrue(resultTrue.contains("This is hidden").not())
        assertTrue(resultFalse.contains("This is visible").not())
        assertTrue(resultFalse.contains("This is hidden"))
    }

    @Test
    fun testListIteration() {
        val template = """
            {{#items}}
            - {{name}}: {{value}}
            {{/items}}
        """.trimIndent()
        
        val data = mapOf("items" to listOf(
            mapOf("name" to "Item 1", "value" to "Value 1"),
            mapOf("name" to "Item 2", "value" to "Value 2"),
            mapOf("name" to "Item 3", "value" to "Value 3")
        ))
        
        val result = compileAndExecute(template, data)
        
        assertTrue(result.contains("Item 1: Value 1"))
        assertTrue(result.contains("Item 2: Value 2"))
        assertTrue(result.contains("Item 3: Value 3"))
    }

    @Test
    fun testNestedObjects() {
        val template = """
            User: {{user.name}}
            Email: {{user.email}}
            Age: {{user.age}}
        """.trimIndent()
        
        val data = mapOf("user" to mapOf(
            "name" to "John Doe",
            "email" to "john@example.com",
            "age" to 30
        ))
        
        val result = compileAndExecute(template, data)
        
        assertTrue(result.contains("User: John Doe"))
        assertTrue(result.contains("Email: john@example.com"))
        assertTrue(result.contains("Age: 30"))
    }

    @Test
    fun testEscaping() {
        val template = "{{name}} and {{{unescaped}}}"
        val data = mapOf(
            "name" to "<script>alert('xss')</script>",
            "unescaped" to "<b>bold</b>"
        )
        
        val result = compileAndExecute(template, data)
        
        // Escaped version should contain HTML entities
        assertTrue(result.contains("&lt;script&gt;alert(&#39;xss&#39;)&lt;/script&gt;"))
        // Unescaped version should contain raw HTML
        assertTrue(result.contains("<b>bold</b>"))
    }

    @Test
    fun testTemplateCompilation() {
        val template = "Hello {{name}}, welcome to {{app}}!"
        val data = mapOf(
            "name" to "User",
            "app" to "ClipDexter"
        )
        
        val result = compileAndExecute(template, data)
        assertEquals("Hello User, welcome to ClipDexter!", result)
    }

    @Test
    fun testEmptyListHandling() {
        val template = """
            {{#items}}
            Item: {{name}}
            {{/items}}
            {{^items}}
            No items found
            {{/items}}
        """.trimIndent()
        
        val dataEmpty = mapOf("items" to emptyList<String>())
        val dataNull = mapOf("items" to null)
        
        val resultEmpty = compileAndExecute(template, dataEmpty)
        val resultNull = compileAndExecute(template, dataNull)
        
        assertTrue(resultEmpty.contains("No items found"))
        assertTrue(resultNull.contains("No items found"))
    }

    private fun compileAndExecute(template: String, data: Any): String {
        val factory = DefaultMustacheFactory()
        val mustache = factory.compile(StringReader(template), "template")
        val writer = StringWriter()
        mustache.execute(writer, data)
        return writer.toString()
    }
} 